package kr.kernel360.anabada.domain.tradeoffer.dto;

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
	private FindTradeOfferDto findTradeOfferDto;

	@Builder
	public static FindTradeOfferResponse of(FindTradeOfferDto findTradeOfferDto) {
		return FindTradeOfferResponse.builder()
			.findTradeOfferDto(findTradeOfferDto)
			.build();
	}

}
