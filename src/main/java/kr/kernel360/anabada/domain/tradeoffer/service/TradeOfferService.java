package kr.kernel360.anabada.domain.tradeoffer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferDto;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.domain.tradeoffer.repository.TradeOfferRepository;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferSearchType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeOfferService {
	private final TradeOfferRepository tradeOfferRepository;
	private final MemberRepository memberRepository;
	private final TradeRepository tradeRepository;

	public Page<FindTradeOfferDto> findAll(TradeOfferSearchType tradeOfferSearchType, FindAllTradeOfferRequest findAllTradeOfferRequest, Pageable pageable) {
		DeletedStatus deletedStatus = DeletedStatus.FALSE;
		Member member = findMemberById(findAllTradeOfferRequest.getMemberId());
		Trade trade = findTradeById(findAllTradeOfferRequest.getTradeId());
		return switch (tradeOfferSearchType) {
			case MEMBER -> tradeOfferRepository.findByMemberAndDeletedStatus(member, deletedStatus, pageable).map(FindTradeOfferDto::of);
			case TRADE -> tradeOfferRepository.findByTradeAndDeletedStatus(trade, deletedStatus, pageable).map(FindTradeOfferDto::of);
		};
	}

	public FindTradeOfferResponse find(Long tradeOfferId) {
		TradeOffer tradeOffer = findTradeOfferById(tradeOfferId);
		FindTradeOfferDto findTradeOfferDto = FindTradeOfferDto.of(tradeOffer);
		return FindTradeOfferResponse.of(findTradeOfferDto);
	}

	@Transactional
	public Long create(CreateTradeOfferRequest createTradeOfferRequest, Long memberId, Long tradeId) {
		Member member = findMemberById(memberId);

		Trade trade = findTradeById(tradeId);

		return tradeOfferRepository.save(CreateTradeOfferRequest.toEntity(createTradeOfferRequest, member, trade)).getId();
	}

	@Transactional
	public UpdateTradeOfferResponse update(UpdateTradeOfferRequest updateTradeOfferRequest) {
		TradeOffer tradeOffer = findTradeOfferById(updateTradeOfferRequest.getId());

		tradeOffer.update(updateTradeOfferRequest.getTitle(), updateTradeOfferRequest.getContent(),
			updateTradeOfferRequest.getImagePath());

		return UpdateTradeOfferResponse.of(tradeOffer);
	}

	@Transactional
	public void remove(Long tradeOfferId) {
		TradeOffer tradeOffer = findTradeOfferById(tradeOfferId);

		tradeOffer.remove();
	}

	private TradeOffer findTradeOfferById(Long id) {
		return tradeOfferRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 교환 요청이 없습니다."));
	}

	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));
	}

	private Trade findTradeById(Long tradeId) {
		return tradeRepository.findById(tradeId).orElseThrow(() -> new IllegalArgumentException(""));
	}
}
