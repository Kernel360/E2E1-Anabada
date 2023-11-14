package kr.kernel360.anabada.domain.tradeoffer.dto;

import org.springframework.data.domain.Page;

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
public class FindAllTradeOfferResponse {
	Page<FindTradeOfferDto> tradeOffers;

	public static FindAllTradeOfferResponse of(Page<FindTradeOfferDto> tradeOffers) {
		return FindAllTradeOfferResponse.builder()
			.tradeOffers(tradeOffers)
			.build();
	}
}
