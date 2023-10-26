package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTradeResponse {
	private Long tradeId;
	private String tradeType;
	private String categoryName;
	private String tradeTitle;
	private String nickname;
	private LocalDateTime createdDate;
	private String content;
	private String imagePath;

	public FindTradeResponse(Long tradeId, String tradeType, String categoryName, String tradeTitle, String nickname,
		LocalDateTime createdDate, String content, String imagePath) {
		this.tradeId = tradeId;
		this.tradeType = tradeType;
		this.categoryName = categoryName;
		this.tradeTitle = tradeTitle;
		this.nickname = nickname;
		this.createdDate = createdDate;
		this.content = content;
		this.imagePath = imagePath;
	}
}
