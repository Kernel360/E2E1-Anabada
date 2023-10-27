package kr.kernel360.anabada.domain.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.dto.CreateTradeRequest;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeDto;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.FindTradeResponse;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {
	private final TradeRepository tradeRepository;
	private final MemberRepository memberRepository;

	public FindAllTradeResponse findAll() {
		List<FindAllTradeDto> findTrades = tradeRepository.findTrades();
		return FindAllTradeResponse.of(findTrades);
	}

	public FindTradeResponse find(Long tradeId) {
		return tradeRepository.findTrade(tradeId);
	}

	@Transactional
	public Long create(CreateTradeRequest createTradeRequest) {
		Member findMember = findMemberById(createTradeRequest.getMemberId());
		// todo : category 조회 기능 개발 후 교환 엔티티에 카테고리 추가 필요
		Trade savedTrade = tradeRepository.save(CreateTradeRequest.toEntity(createTradeRequest, findMember));
		return savedTrade.getId();
	}

	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
	}

}
