package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTradeOfferDto {
		@ApiModelProperty(value = "교환 요청 아이디", example = "1")
		private Long id;

		@ApiModelProperty(value = "교환 요청 제목", example = "아이폰 드려요")
		private String title;

		@ApiModelProperty(value = "교환 요청 내용", example = "아이폰 13 드려요")
		private String content;

		@ApiModelProperty(value = "이미지 경로"
			, example = "'src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png'")
		private String imagePath;

		@ApiModelProperty(value = "교환 요청 거래 상태", example = "수락전")
		private TradeOfferStatus tradeOfferStatus;

		@ApiModelProperty(value = "교환 요청 삭제 상태", example = "정상")
		private DeletedStatus deletedStatus;

		@ApiModelProperty(value = "작성자", example = "겨울붕어빵이윤선")
		private String createdBy;

		@ApiModelProperty(value = "교환 아이디", example = "1")
		private Long tradeId;

		@ApiModelProperty(value = "작성일", example = "2023-11-11")
		private String createdDate;

		@ApiModelProperty(value = "교환 제목", example = "아이폰 받고 갤럭시 드려요")
		private String tradeTitle;

		@ApiModelProperty(value = "교환 작성자", example = "가을전어장호윤")
		private String tradeMemberNickname;

	@QueryProjection
	@Builder
	public FindTradeOfferDto(Long id, String title, String content, String imagePath, TradeOfferStatus tradeOfferStatus,
		DeletedStatus deletedStatus, String createdBy, LocalDateTime createdDate, Long tradeId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.imagePath = imagePath;
		this.tradeOfferStatus = tradeOfferStatus;
		this.deletedStatus = deletedStatus;
		this.createdBy = createdBy;
		this.createdDate = DateParser.dateTimeToString(createdDate);
		this.tradeId = tradeId;
	}

	@QueryProjection
	@Builder
	public FindTradeOfferDto(Long id, String title, TradeOfferStatus tradeOfferStatus, DeletedStatus deletedStatus,
		String createdBy, Long tradeId, LocalDateTime createdDate, String tradeTitle, String tradeMemberNickname) {
		this.id = id;
		this.title = title;
		this.tradeOfferStatus = tradeOfferStatus;
		this.deletedStatus = deletedStatus;
		this.createdBy = createdBy;
		this.tradeId = tradeId;
		this.createdDate = DateParser.dateTimeToString(createdDate);
		this.tradeTitle = tradeTitle;
		this.tradeMemberNickname = tradeMemberNickname;
	}
}
