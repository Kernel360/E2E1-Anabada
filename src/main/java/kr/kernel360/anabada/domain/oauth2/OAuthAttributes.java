package kr.kernel360.anabada.domain.oauth2;

import java.util.Map;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.oauth2.memberinfo.KakaoOAuth2MemberInfo;
import kr.kernel360.anabada.domain.oauth2.memberinfo.OAuth2MemberInfo;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private String nameAttributeKey;

	private OAuth2MemberInfo oAuth2MemberInfo;

	@Builder
	public OAuthAttributes(String nameAttributeKey, OAuth2MemberInfo oAuth2MemberInfo) {
		this.nameAttributeKey = nameAttributeKey;
		this.oAuth2MemberInfo = oAuth2MemberInfo;
	}

	public static OAuthAttributes of(SocialProvider socialProvider
	, String userNameAttributeName, Map<String, Object> attributes) {
		return ofKakao(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.nameAttributeKey(userNameAttributeName)
			.oAuth2MemberInfo(new KakaoOAuth2MemberInfo(attributes))
			.build();
	}

	//password는 아무런 역할이 없음 임시 해쉬값
	public static Member toEntity(SocialProvider socialProvider, OAuth2MemberInfo oAuth2MemberInfo) {
		return Member.builder()
			.socialProvider(socialProvider)
			.socialId(oAuth2MemberInfo.getId())
			.password(String.valueOf(oAuth2MemberInfo.getId().hashCode()))
			.email(socialProvider.getDescription() + oAuth2MemberInfo.getEmail())
			.authorities("ROLE_USER")
			.accountStatus(true)
			.nickname(oAuth2MemberInfo.getNickname())
			.gender(oAuth2MemberInfo.getGender())
			.birth(oAuth2MemberInfo.getBirth())
			.build();
	}
}