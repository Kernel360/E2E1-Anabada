package kr.kernel360.anabada.domain.trade.dto;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTradeRequest {
	private String title;
	private String content;
	private String imagePath;
	private String tradeType;
	private TradeStatus tradeStatus = TradeStatus.BEFORE_ACCEPT;
	private Long categoryId;
	private Long memberId;

	public static Trade toEntity(CreateTradeRequest createTradeRequest, Member member) {
		return Trade.builder()
			.title(createTradeRequest.title)
			.content(createTradeRequest.content)
			.imagePath(createTradeRequest.imagePath)
			.tradeType(TradeType.valueOf(createTradeRequest.tradeType))
			.tradeStatus(createTradeRequest.tradeStatus)
			.member(member)
			.build();
	}
}
