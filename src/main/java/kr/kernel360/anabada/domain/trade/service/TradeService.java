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
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {
	private final TradeRepository tradeRepository;
	private final MemberRepository memberRepository;
	private final CategoryRepository categoryRepository;
	private final PlaceRepository placeRepository;


	public FindAllTradeResponse findAll(TradeSearchCondition tradeSearchCondition, Pageable pageable) {
		Page<FindTradeDto> findTrades = tradeRepository.findTrades(tradeSearchCondition, pageable);
		return FindAllTradeResponse.of(findTrades);
	}

	public FindTradeResponse find(Long tradeId) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member loginMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다"));

		FindTradeDto findTradeDto = Optional.of(tradeRepository.findTrade(tradeId))
			.orElseThrow(() -> new IllegalArgumentException("해당하는 교환이 없습니다."));

		Member tradeOwner = memberRepository.findByNickname(findTradeDto.getNickname())
			.orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다."));

		FindTradeResponse findTradeResponse = FindTradeResponse.of(findTradeDto);
		findTradeResponse.setIsTradeOwner(loginMember.getEmail().equals(tradeOwner.getEmail()));

		return findTradeResponse;
	}

	@Transactional
	public Long create(CreateTradeRequest createTradeRequest, PlaceDto placeDto) {

		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다"));

		Category category = categoryRepository.findById(createTradeRequest.getCategoryId())
			.orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));

		Place place = placeRepository.save(PlaceDto.toEntity(placeDto));

		Trade savedTrade = tradeRepository.save(CreateTradeRequest.toEntity(createTradeRequest, category, member, place));
		return savedTrade.getId();
	}

}
