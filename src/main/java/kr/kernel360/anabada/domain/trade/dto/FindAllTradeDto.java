package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindAllTradeDto {
	private Long tradeId;

	private String tradeType;

	private String categoryName;

	private String tradeTitle;

	private String nickname;

	private LocalDateTime createdDate;

}
