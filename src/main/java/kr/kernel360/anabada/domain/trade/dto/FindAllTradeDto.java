package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindAllTradeDto {
	private Long tradeId;

	private TradeType tradeType;

	private String categoryName;

	private String tradeTitle;

	private String nickname;

	private LocalDateTime createdDate;

}
