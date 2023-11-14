package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTradeOfferDto {
		private Long id;

		private String title;

		private String content;

		private String imagePath;

		private TradeOfferStatus tradeOfferStatus;

		private DeletedStatus deletedStatus;

		private String createdBy;

		private Long tradeId;

		private LocalDateTime createdDate;

	@QueryProjection
	@Builder
	public FindTradeOfferDto(Long id, String title, TradeOfferStatus tradeOfferStatus, DeletedStatus deletedStatus,
		String createdBy, Long tradeId, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.tradeOfferStatus = tradeOfferStatus;
		this.deletedStatus = deletedStatus;
		this.createdBy = createdBy;
		this.tradeId = tradeId;
		this.createdDate = createdDate;
	}

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
		this.createdDate = createdDate;
		this.tradeId = tradeId;
	}
}
