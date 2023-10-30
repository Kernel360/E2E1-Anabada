package kr.kernel360.anabada.domain.tradeoffer.service;

import org.springframework.stereotype.Service;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.domain.tradeoffer.repository.TradeOfferRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeOfferService {
	private final TradeOfferRepository tradeOfferRepository;
	private final MemberRepository memberRepository;
	private final TradeRepository tradeRepository;

	public FindAllTradeOfferResponse findAll(FindAllTradeOfferRequest findAllTradeOfferRequest) {

		return FindAllTradeOfferResponse.builder()
			.build();
	}

	public Long create(CreateTradeOfferRequest createTradeOfferRequest, Long memberId, Long tradeId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));

		Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new IllegalArgumentException(""));

		return tradeOfferRepository.save(CreateTradeOfferRequest.toEntity(createTradeOfferRequest, member, trade)).getId();
	}

	public UpdateTradeOfferResponse update(UpdateTradeOfferRequest updateTradeOfferRequest) {
		TradeOffer tradeOffer = tradeOfferRepository.findById(updateTradeOfferRequest.getId())
			.orElseThrow(() -> new IllegalArgumentException("해당하는 교환 요청이 없습니다."));

		tradeOffer.update(updateTradeOfferRequest.getTitle(), updateTradeOfferRequest.getContent(),
			updateTradeOfferRequest.getImagePath());

		return UpdateTradeOfferResponse.of(tradeOffer);
	}
}
