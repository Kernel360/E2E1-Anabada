package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.time.LocalDateTime;

import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindTradeOfferDto {
	private Long id;

	private String title;

	private String content;

	private String createdBy;

	private LocalDateTime createdDate;

	public FindTradeOfferDto of(TradeOffer tradeOffer) {
		return FindTradeOfferDto.builder()
			.id(tradeOffer.getId())
			.title(tradeOffer.getTitle())
			.content(tradeOffer.getContent())
			.createdBy(tradeOffer.getMember().getNickname())
			.createdDate(tradeOffer.getCreateDate())
			.build();
	}

	@Builder
	public FindTradeOfferDto(Long id, String title, String content,
		String createdBy, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
}
