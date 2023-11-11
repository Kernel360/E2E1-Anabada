package kr.kernel360.anabada.domain.trade.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TradeSearchCondition {
	private String tradeType;
	private String categoryName;

	public TradeSearchCondition(String tradeType, String categoryName) {
		this.tradeType = tradeType;
		this.categoryName = categoryName;
	}
}
