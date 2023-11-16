package kr.kernel360.anabada.global.commons.domain;

public enum TradeOfferStatus {
	REQUEST_ON_HOLD("요청대기"),
	REQUEST_ACCEPTED("요청수락"),
	REQUEST_EXPIRED("요청만료");

	private final String description;

	TradeOfferStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
