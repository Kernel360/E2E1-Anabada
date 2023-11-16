package kr.kernel360.anabada.domain.tradeoffer.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "교환 요청 제목", example = "갤럭시 드려요.")
	private String title;

	@ApiModelProperty(value = "교환 요청 내용", example = "갤럭시랑 아이폰 바꾸자 이 녀석아!")
	private String content;

	@ApiModelProperty(value = "이미지 경로"
		, example = "'src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png'")
	private String imagePath;

	@ApiModelProperty(value = "교환 요청 상태", example = "요청대기")
	private final TradeOfferStatus tradeOfferStatus = TradeOfferStatus.REQUEST_ON_HOLD;

	@ApiModelProperty(value = "교환 요청 삭제 상태", example = "정상")
	private final DeletedStatus deletedStatus = DeletedStatus.FALSE;

	@ApiModelProperty(value = "교환 아이디", example = "1")
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
