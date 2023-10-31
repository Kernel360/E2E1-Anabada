package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.time.LocalDateTime;

import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	public static FindTradeOfferDto of(TradeOffer tradeOffer) {
		return FindTradeOfferDto.builder()
			.id(tradeOffer.getId())
			.title(tradeOffer.getTitle())
			.imagePath(tradeOffer.getImagePath())
			.tradeOfferStatus(tradeOffer.getTradeOfferStatus())
			.deletedStatus(tradeOffer.getDeletedStatus())
			.content(tradeOffer.getContent())
			.createdBy(tradeOffer.getMember().getNickname())
			.tradeId(tradeOffer.getTrade().getId())
			.createdDate(tradeOffer.getCreatedDate())
			.build();
	}

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
