package kr.kernel360.anabada.global.commons.domain;

public enum SocialProvider {
	KAKAO("kakao"),
	LOCAL("local");

	private final String description;

	SocialProvider(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
