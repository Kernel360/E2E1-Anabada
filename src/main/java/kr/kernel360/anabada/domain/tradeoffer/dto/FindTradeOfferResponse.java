package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.time.LocalDateTime;

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
public class FindTradeOfferResponse {
	private Long id;

	private String title;

	private String content;

	private String imagePath;

	private TradeOfferStatus tradeOfferStatus;

	private DeletedStatus deletedStatus;

	private String createdBy;

	private Long tradeId;

	private LocalDateTime createdDate;

	public static FindTradeOfferResponse of(FindTradeOfferDto findTradeOfferDto) {
		return FindTradeOfferResponse.builder()
			.id(findTradeOfferDto.getId())
			.title(findTradeOfferDto.getTitle())
			.content(findTradeOfferDto.getContent())
			.imagePath(findTradeOfferDto.getImagePath())
			.tradeOfferStatus(findTradeOfferDto.getTradeOfferStatus())
			.deletedStatus(findTradeOfferDto.getDeletedStatus())
			.createdBy(findTradeOfferDto.getCreatedBy())
			.tradeId(findTradeOfferDto.getTradeId())
			.createdDate(findTradeOfferDto.getCreatedDate())
			.build();
	}
}
