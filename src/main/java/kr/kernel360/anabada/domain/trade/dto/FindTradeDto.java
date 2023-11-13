package kr.kernel360.anabada.domain.trade.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTradeDto {
	private Long tradeId;

	private TradeType tradeType;
  
	private TradeStatus tradeStatus;

	private DeletedStatus deletedStatus;

	private String categoryName;

	private String tradeTitle;

	private String nickname;

	private String createdDate;

	private String content;

	private String imagePath;

	@QueryProjection
	@Builder
	public FindTradeDto(Long tradeId, TradeType tradeType, TradeStatus tradeStatus, DeletedStatus deletedStatus, String categoryName, String tradeTitle, String nickname,
		LocalDateTime createdDate) {
		this.tradeId = tradeId;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.deletedStatus = deletedStatus;
		this.categoryName = categoryName;
		this.tradeTitle = tradeTitle;
		this.nickname = nickname;
		this.createdDate = DateParser.dateToString(createdDate);
	}

	@Builder
	public FindTradeDto(Long tradeId, TradeType tradeType, TradeStatus tradeStatus, DeletedStatus deletedStatus,
		String categoryName, String tradeTitle, String nickname, LocalDateTime createdDate, String content,
		String imagePath) {
		this.tradeId = tradeId;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.deletedStatus = deletedStatus;
		this.categoryName = categoryName;
		this.tradeTitle = tradeTitle;
		this.nickname = nickname;
		this.createdDate = DateParser.dateTimeToString(createdDate);
		this.content = content;
		this.imagePath = imagePath;
	}
}
