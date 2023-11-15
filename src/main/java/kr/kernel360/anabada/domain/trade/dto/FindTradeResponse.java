package kr.kernel360.anabada.domain.trade.dto;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindTradeResponse {
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

	private Boolean isTradeOwner;

	public static FindTradeResponse of(FindTradeDto findTradeDto) {
		return FindTradeResponse.builder()
			.tradeId(findTradeDto.getTradeId())
			.tradeType(findTradeDto.getTradeType())
			.tradeStatus(findTradeDto.getTradeStatus())
			.deletedStatus(findTradeDto.getDeletedStatus())
			.categoryName(findTradeDto.getCategoryName())
			.tradeTitle(findTradeDto.getTradeTitle())
			.nickname(findTradeDto.getNickname())
			.createdDate(findTradeDto.getCreatedDate())
			.content(findTradeDto.getContent())
			.imagePath(findTradeDto.getImagePath())
			.build();
	}
}
