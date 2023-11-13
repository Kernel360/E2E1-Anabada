package kr.kernel360.anabada.domain.trade.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradeSearchCondition {
	private String title;
	private String createdBy;
	private String tradeType;
	private String categoryName;
}
