package kr.kernel360.anabada.domain.trade.dto;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllTradeResponse {
	@JsonProperty(value = "trades")
	private Page<FindTradeDto> trades;

	public static FindAllTradeResponse of(Page<FindTradeDto> trades) {
		return FindAllTradeResponse.builder()
			.trades(trades)
			.build();
	}
}
