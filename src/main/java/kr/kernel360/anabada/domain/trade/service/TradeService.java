package kr.kernel360.anabada.domain.trade.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.category.repository.CategoryRepository;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.place.entity.Place;
import kr.kernel360.anabada.domain.place.repository.PlaceRepository;
import kr.kernel360.anabada.domain.trade.dto.CreateTradeRequest;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.FindTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import kr.kernel360.anabada.global.error.code.TradeErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {
	private final TradeRepository tradeRepository;
	private final MemberRepository memberRepository;
	private final CategoryRepository categoryRepository;
	private final PlaceRepository placeRepository;

	@Transactional
	public FindAllTradeResponse findAll(TradeSearchCondition tradeSearchCondition, PlaceDto placeDto, Pageable pageable) {
		Place findPlace = findPlaceByStateAndCityAndAddress1(placeDto);
		Page<FindTradeDto> findTrades = tradeRepository.findTrades(tradeSearchCondition, findPlace, pageable);

		return FindAllTradeResponse.of(findTrades);
	}

	public FindTradeResponse find(Long tradeId) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member findLoginMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(TradeErrorCode.NOT_FOUND_MEMBER));
		FindTradeDto findTradeDto = Optional.of(tradeRepository.findTrade(tradeId))
			.orElseThrow(() -> new BusinessException(TradeErrorCode.NOT_FOUND_TRADE));
		Member findTradeOwner = memberRepository.findByNickname(findTradeDto.getNickname())
			.orElseThrow(()-> new BusinessException(TradeErrorCode.NOT_FOUND_MEMBER));
		FindTradeResponse findTradeResponse = FindTradeResponse.of(findTradeDto);
		findTradeResponse.setIsTradeOwner(findLoginMember.getEmail().equals(findTradeOwner.getEmail()));
		findTradeResponse.setIsTradeOffer(tradeRepository.countTradeOfferByTradeIdAndEmail(tradeId, findEmailByJwt) > 0 ? true : false);

		return findTradeResponse;
	}

	@Transactional
	public Long create(CreateTradeRequest createTradeRequest, PlaceDto placeDto) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member findMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(TradeErrorCode.NOT_FOUND_MEMBER));
		Category findCategory = categoryRepository.findById(createTradeRequest.getCategoryId())
			.orElseThrow(() -> new BusinessException(TradeErrorCode.NOT_FOUND_CATEGORY));
		Place findPlace = findPlaceByStateAndCityAndAddress1(placeDto);
		Trade savedTrade = tradeRepository.save(CreateTradeRequest.toEntity(createTradeRequest, findCategory, findMember, findPlace));

		return savedTrade.getId();
	}

	@Transactional
	public Place findPlaceByStateAndCityAndAddress1(PlaceDto placeDto) {
		if (!placeRepository.existsByStateAndCityAndAddress1(placeDto.getState(), placeDto.getCity(),
			placeDto.getAddress1())) {
			return placeRepository.save(PlaceDto.toEntity(placeDto));
		} else {
			return placeRepository.findByStateAndCityAndAddress1(placeDto.getState(), placeDto.getCity(),
				placeDto.getAddress1()).orElseThrow(() -> new IllegalArgumentException("wd"));
		}
	}
}
