package kr.kernel360.anabada.domain.tradeoffer.dto;

import java.util.List;

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
	List<FindTradeOfferDto> tradeOffers;

	public static FindAllTradeOfferResponse of(List<FindTradeOfferDto> tradeOffers) {
		return FindAllTradeOfferResponse.builder()
			.tradeOffers(tradeOffers)
			.build();
	}
}
