package kr.kernel360.anabada.global.commons.domain;

public enum TradeStatus {
	BEFORE_ACCEPT("수락전"),
	AFTER_ACCEPT("수락후");

	private final String description;

	TradeStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}






