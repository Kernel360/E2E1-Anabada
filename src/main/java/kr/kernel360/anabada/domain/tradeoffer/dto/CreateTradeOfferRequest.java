package kr.kernel360.anabada.domain.tradeoffer.dto;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTradeOfferRequest {
	private String title;

	private String content;

	private String imagePath;

	private TradeOfferStatus tradeOfferStatus = TradeOfferStatus.REQUEST_ON_HOLD;

	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	private Long memberId;

	private Long tradeId;

	public static TradeOffer toEntity(CreateTradeOfferRequest createTradeOfferRequest, Member member, Trade trade) {
		return TradeOffer.builder()
			.title(createTradeOfferRequest.title)
			.content(createTradeOfferRequest.content)
			.imagePath(createTradeOfferRequest.imagePath)
			.tradeOfferStatus(createTradeOfferRequest.tradeOfferStatus)
			.deletedStatus(createTradeOfferRequest.deletedStatus)
			.member(member)
			.trade(trade)
			.build();
	}
}
