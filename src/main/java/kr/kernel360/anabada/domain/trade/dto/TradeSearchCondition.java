package kr.kernel360.anabada.domain.trade.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradeSearchCondition {
	@ApiModelProperty(value = "교환 제목", example = "[강아지] 드려요. [고양이] 받아요.")
	private String title;

	@ApiModelProperty(value = "작성자", example = "가을전어이윤선")
	private String createdBy;

	@ApiModelProperty(value = "교환 타입", example = "바꿔요")
	private String tradeType;

	@ApiModelProperty(value = "카테고리 아이디", example = "1")
	private Long categoryId;
}
