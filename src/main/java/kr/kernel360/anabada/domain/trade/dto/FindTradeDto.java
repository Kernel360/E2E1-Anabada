package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTradeDto {
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


	@JsonProperty(value = "trade_offer_created_by")
	@ApiModelProperty(value = "교환 요청 작성자", example = "가을전어이윤선")
	private String tradeOfferCreatedBy;

	@QueryProjection
	@Builder
	public FindTradeDto(Long tradeId, TradeType tradeType, TradeStatus tradeStatus, DeletedStatus deletedStatus,
		String categoryName, String tradeTitle, String nickname,
		LocalDateTime createdDate, String tradeOfferCreatedBy) {
		this.tradeId = tradeId;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.deletedStatus = deletedStatus;
		this.categoryName = categoryName;
		this.tradeTitle = tradeTitle;
		this.nickname = nickname;
		this.createdDate = DateParser.dateToString(createdDate);
		this.tradeOfferCreatedBy = tradeOfferCreatedBy;
	}

	@QueryProjection
	@Builder
	public FindTradeDto(Long tradeId, TradeType tradeType, TradeStatus tradeStatus, DeletedStatus deletedStatus,
		String categoryName, String tradeTitle, String nickname, LocalDateTime createdDate, String content,
		String imagePath) {
		this.tradeId = tradeId;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.deletedStatus = deletedStatus;
		this.categoryName = categoryName;
		this.tradeTitle = tradeTitle;
		this.nickname = nickname;
		this.createdDate = DateParser.dateTimeToString(createdDate);
		this.content = content;
		this.imagePath = imagePath;
	}

}
