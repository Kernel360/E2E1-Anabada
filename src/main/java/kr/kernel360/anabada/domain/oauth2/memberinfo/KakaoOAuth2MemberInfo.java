package kr.kernel360.anabada.domain.oauth2.memberinfo;

import java.util.Map;

public class KakaoOAuth2MemberInfo extends OAuth2MemberInfo{
	public KakaoOAuth2MemberInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getNickname() {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) account.get("profile");

		if (account == null || profile == null) {
			return null;
		}

		return (String) profile.get("nickname");
	}

	@Override
	public String getEmail() {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

		if (account == null) {
			return null;
		}

		return (String) account.get("email");
	}

	@Override
	public String getBirth() {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

		if (account == null) {
			return null;
		}
		String birthyear = (String) account.get("birthyear");
		String birthday = (String) account.get("birthday");

		return birthyear + "-" + birthday.substring(0, 2) + "-" + birthday.substring(2, 4);
	}

	@Override
	public String getGender() {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

		if (account == null) {
			return null;
		}

		String gender = (String)account.get("gender");
		Character genderUpperCase = Character.toUpperCase(gender.charAt(0));

		return String.valueOf(genderUpperCase);
	}
}
