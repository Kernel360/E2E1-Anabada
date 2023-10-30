package kr.kernel360.anabada.global.commons.domain;

/**
* Deleted status for {@link kr.kernel360.anabada.domain.trade.entity.Trade}, {@link kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer}
 */

public enum DeletedStatus {
	TRUE("삭제")
	, FALSE("정상");

	private final String description;

	DeletedStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
