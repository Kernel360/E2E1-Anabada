package kr.kernel360.anabada.domain.trade.dto;

import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.place.entity.Place;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
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

	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	private Long categoryId;

	private Long memberId;

	public static Trade toEntity(CreateTradeRequest createTradeRequest, Category category, Member member, Place place) {
		return Trade.builder()
			.title(createTradeRequest.title)
			.content(createTradeRequest.content)
			.imagePath(createTradeRequest.imagePath)
			.tradeType(TradeType.valueOf(createTradeRequest.tradeType))
			.tradeStatus(createTradeRequest.tradeStatus)
			.deletedStatus(createTradeRequest.deletedStatus)
			.category(category)
			.member(member)
			.place(place)
			.build();
	}
}
