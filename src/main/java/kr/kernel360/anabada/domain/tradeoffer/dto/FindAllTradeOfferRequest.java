package kr.kernel360.anabada.domain.tradeoffer.dto;

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
public class FindAllTradeOfferRequest {
	private TradeOfferStatus tradeOfferStatus = TradeOfferStatus.REQUEST_ON_HOLD;

	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	private Long memberId;

	private Long tradeId;

	private String nickname;

}
