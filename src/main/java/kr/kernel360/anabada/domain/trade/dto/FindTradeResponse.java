package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

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
	private String tradeType;
	private String categoryName;
	private String tradeTitle;
	private String nickname;
	private LocalDateTime createdDate;
	private String content;
	private String imagePath;
}
