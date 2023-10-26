package kr.kernel360.anabada.domain.trade.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllTradeResponse {
	private List<FindAllTradeDto> trades;

	public static FindAllTradeResponse of(List<FindAllTradeDto> trades) {
		return FindAllTradeResponse.builder()
			.trades(trades)
			.build();
	}
}
