package kr.kernel360.anabada.domain.trade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindTradeResponse {
	@JsonProperty(value = "trade_id")
	@ApiModelProperty(value = "교환 아이디", example = "1")
	private Long tradeId;

	@JsonProperty(value = "trade_type")
	@ApiModelProperty(value = "교환 타입", example = "바꿔요")
	private TradeType tradeType;

	@JsonProperty(value = "trade_status")
	@ApiModelProperty(value = "교환 거래 상태", example = "수락전")
	private TradeStatus tradeStatus;

	@JsonProperty(value = "deleted_status")
	@ApiModelProperty(value = "교환 삭제 상태", example = "정상")
	private DeletedStatus deletedStatus;

	@JsonProperty(value = "category_name")
	@ApiModelProperty(value = "카테고리 이름", example = "전자기기")
	private String categoryName;

	@JsonProperty(value = "trade_title")
	@ApiModelProperty(value = "교환 제목", example = "'[아이폰]드려요. [갤럭시]받아요.'")
	private String tradeTitle;

	@ApiModelProperty(value = "교환 작성자", example = "여름수박장호윤")
	private String nickname;

	@JsonProperty(value = "created_date")
	@ApiModelProperty(value = "교환 작성일", example = "2023-10-11")
	private String createdDate;

	@ApiModelProperty(value = "교환 내용", example = "아이폰 13 pro랑 갤럭시 22 바꿔요.")
	private String content;

	@JsonProperty(value = "image_path")
	@ApiModelProperty(value = "이미지 경로"
		, example = "'src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png'")
	private String imagePath;

	@JsonProperty(value = "is_trade_owner")
	@ApiModelProperty(value = "교환 작성자 확인", example = "true")
	private Boolean isTradeOwner;

	@JsonProperty(value = "is_trade_offer")
	@ApiModelProperty(value = "교환 요청 작성자 확인", example = "true")
	private Boolean isTradeOffer;

	public static FindTradeResponse of(FindTradeDto findTradeDto) {
		return FindTradeResponse.builder()
			.tradeId(findTradeDto.getTradeId())
			.tradeType(findTradeDto.getTradeType())
			.tradeStatus(findTradeDto.getTradeStatus())
			.deletedStatus(findTradeDto.getDeletedStatus())
			.categoryName(findTradeDto.getCategoryName())
			.tradeTitle(findTradeDto.getTradeTitle())
			.nickname(findTradeDto.getNickname())
			.createdDate(findTradeDto.getCreatedDate())
			.content(findTradeDto.getContent())
			.imagePath(findTradeDto.getImagePath())
			.build();
	}
}
