package kr.kernel360.anabada.global.commons.domain;

public enum TradeOfferSearchType {
	MEMBER("회원"),
	TRADE("교환");

	private final String description;

	TradeOfferSearchType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}


