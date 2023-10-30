package kr.kernel360.anabada.domain.trade.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllTradeResponse {
	private List<FindTradeDto> trades;

	public static FindAllTradeResponse of(List<FindTradeDto> trades) {
		return FindAllTradeResponse.builder()
			.trades(trades)
			.build();
	}
}
