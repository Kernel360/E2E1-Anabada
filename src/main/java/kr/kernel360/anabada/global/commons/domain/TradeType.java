package kr.kernel360.anabada.global.commons.domain;

public enum TradeType {
	REQUEST("받아요"),
	TRADE("바꿔요"),
	SHARE("드려요");

	private final String description;

	TradeType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
