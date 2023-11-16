package kr.kernel360.anabada.domain.trade.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "교환 제목", example = "'[아이폰]드려요. [갤럭시]받아요.'")
	private String title;

	@ApiModelProperty(value = "교환 내용", example = "아이폰 13 pro랑 갤럭시 22 바꿔요.")
	private String content;

	@ApiModelProperty(value = "이미지 경로"
		, example = "'src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png'")
	private String imagePath;

	@ApiModelProperty(value = "교환 타입", example = "바꿔요")
	private String tradeType;

	@ApiModelProperty(value = "교환 거래 상태", example = "수락전")
	private TradeStatus tradeStatus = TradeStatus.BEFORE_ACCEPT;

	@ApiModelProperty(value = "교환 삭제 상태", example = "정상")
	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	@ApiModelProperty(value = "카테고리 아이디", example = "1")
	private Long categoryId;

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
