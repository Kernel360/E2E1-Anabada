package kr.kernel360.anabada.domain.tradeoffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
	@JsonProperty(value = "find_trade_offer_dto")
	private FindTradeOfferDto findTradeOfferDto;

	@JsonProperty(value = "is_offer_owner")
	@ApiModelProperty(value = "교환 작성자 확인", example = "true")
	private Boolean isOfferOwner;

	@Builder
	public static FindTradeOfferResponse of(FindTradeOfferDto findTradeOfferDto) {
		return FindTradeOfferResponse.builder()
			.findTradeOfferDto(findTradeOfferDto)
			.build();
	}

}
