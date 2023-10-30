package kr.kernel360.anabada.domain.tradeoffer.dto;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
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

	private TradeStatus tradeOfferStatus = TradeStatus.BEFORE_ACCEPT;

	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	private Long memberId;

	private Long tradeId;

	public static TradeOffer toEntity(CreateTradeOfferRequest createTradeOfferRequest, Member member, Trade trade) {
		return TradeOffer.builder()
			.title(createTradeOfferRequest.getTitle())
			.content(createTradeOfferRequest.getContent())
			.imagePath(createTradeOfferRequest.getImagePath())
			.tradeOfferStatus(createTradeOfferRequest.getTradeOfferStatus())
			.deletedStatus(createTradeOfferRequest.getDeletedStatus())
			.member(member)
			.trade(trade)
			.build();
	}
}
