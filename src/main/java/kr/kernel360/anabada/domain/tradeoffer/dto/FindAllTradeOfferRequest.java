package kr.kernel360.anabada.domain.tradeoffer.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "교환 요청 상태", example = "요청대기")
	private final TradeOfferStatus tradeOfferStatus = TradeOfferStatus.REQUEST_ON_HOLD;

	@ApiModelProperty(value = "교환 삭제 상태", example = "false")
	private final DeletedStatus deletedStatus = DeletedStatus.FALSE;

	@ApiModelProperty(value = "회원 아이디", example = "1")
	private Long memberId;

	@ApiModelProperty(value = "교환 아이디", example = "1")
	private Long tradeId;

	@ApiModelProperty(value = "작성자 닉네임", example = "가을전어이윤선")
	private String nickname;
}
