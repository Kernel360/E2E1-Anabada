package kr.kernel360.anabada.domain.tradeoffer.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferDto;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.domain.tradeoffer.repository.TradeOfferRepository;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.error.code.TradeOfferErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeOfferService {
	private final TradeOfferRepository tradeOfferRepository;
	private final MemberRepository memberRepository;
	private final TradeRepository tradeRepository;

	public FindAllTradeOfferResponse findAll(FindAllTradeOfferRequest findAllTradeOfferRequest, Pageable pageable) {
		Page<FindTradeOfferDto> tradeOffers = tradeOfferRepository.findAll(findAllTradeOfferRequest, pageable);
		
		return FindAllTradeOfferResponse.of(tradeOffers);
	}

	public FindTradeOfferResponse find(Long tradeOfferId) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member loginMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(TradeOfferErrorCode.NOT_FOUND_MEMBER));
		FindTradeOfferDto findTradeOfferDto = Optional.ofNullable(tradeOfferRepository.find(tradeOfferId))
			.orElseThrow(() -> new BusinessException(TradeOfferErrorCode.NOT_FOUND_TRADE_OFFER));
		Member tradeOfferOwner = memberRepository.findByNickname(findTradeOfferDto.getCreatedBy())
			.orElseThrow(()-> new BusinessException(TradeOfferErrorCode.NOT_FOUND_MEMBER));
		FindTradeOfferResponse findTradeOfferResponse = FindTradeOfferResponse.of(findTradeOfferDto);
		findTradeOfferResponse.setIsOfferOwner(loginMember.getEmail().equals(tradeOfferOwner.getEmail()));

		return findTradeOfferResponse;
	}

	@Transactional
	public Long create(CreateTradeOfferRequest createTradeOfferRequest, Long tradeId) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member findMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(TradeOfferErrorCode.NOT_FOUND_MEMBER));
		Trade findTrade = findTradeById(tradeId);

		return tradeOfferRepository.save(CreateTradeOfferRequest.toEntity(createTradeOfferRequest, findMember, findTrade)).getId();
	}

	@Transactional
	public Long remove(Long tradeOfferId) {
		TradeOffer findTradeOffer = findTradeOfferById(tradeOfferId);
		findTradeOffer.remove();
		
		return tradeOfferId;
	}

	private TradeOffer findTradeOfferById(Long id) {
		return tradeOfferRepository.findById(id)
			.orElseThrow(() -> new BusinessException(TradeOfferErrorCode.NOT_FOUND_TRADE_OFFER));
	}


	private Trade findTradeById(Long tradeId) {
		return tradeRepository.findById(tradeId)
			.orElseThrow(() -> new BusinessException(TradeOfferErrorCode.NOT_FOUND_TRADE));
	}

	@Transactional
	public void accept(Long tradeOfferId) {
		TradeOffer findTradeOffer = findTradeOfferById(tradeOfferId);
		Trade findTrade = findTradeOffer.getTrade();
		isAlreadyAfterAccept(findTrade);
		findTrade.update(TradeStatus.AFTER_ACCEPT);
		findTradeOffer.update(TradeOfferStatus.REQUEST_ACCEPTED);
		tradeOfferRepository.updateTradeOffersByTradeOfferIdNeAndTradeEq(tradeOfferId, findTrade);
	}

	private void isAlreadyAfterAccept(Trade findTrade) {
		if (findTrade.getTradeStatus() == TradeStatus.AFTER_ACCEPT) {
			throw new BusinessException(TradeOfferErrorCode.ALREADY_AFTER_ACCEPT);
		}
	}
}
