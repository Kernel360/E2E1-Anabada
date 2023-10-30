package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindTradeResponse {
	private Long tradeId;

	private TradeType tradeType;
  
	private TradeStatus tradeStatus;

	private DeletedStatus deletedStatus;

	private String categoryName;

	private String tradeTitle;

	private String nickname;

	private LocalDateTime createdDate;

	private String content;

	private String imagePath;
}
