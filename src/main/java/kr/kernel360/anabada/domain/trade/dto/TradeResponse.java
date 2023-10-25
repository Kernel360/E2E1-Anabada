package kr.kernel360.anabada.domain.trade.dto;

import java.util.List;

import kr.kernel360.anabada.domain.trade.entity.Trade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TradeResponse {
	private List<Trade> trades;

	public static TradeResponse of(List<Trade> trades) {
		return TradeResponse.builder()
			.trades(trades)
			.build();
	}
}
