package kr.kernel360.anabada.domain.oauth2.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.oauth2.CustomOAuth2User;
import kr.kernel360.anabada.domain.oauth2.OAuthAttributes;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		SocialProvider socialProvider = getSocialProvider(registrationId);
		String userNameAttibuteName = userRequest.getClientRegistration()
			.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();
		Map<String, Object> attributes = oAuth2User.getAttributes();

		OAuthAttributes extractAttributes = OAuthAttributes.of(socialProvider, userNameAttibuteName, attributes);

		Member member = findMember(extractAttributes, socialProvider);

		return new CustomOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(member.getAuthorities())),
			attributes,
			extractAttributes.getNameAttributeKey(),
			member.getEmail()
		);
	}

	private SocialProvider getSocialProvider(String registrationId) {
		return SocialProvider.KAKAO;
	}

	private Member findMember(OAuthAttributes attributes, SocialProvider socialProvider) {
		Member member = memberRepository
			.findByEmail(attributes
			.getOAuth2MemberInfo()
			.getEmail())
			.orElse(null);

		if (member == null) {
			return saveMember(attributes, socialProvider);
		} else if (member.getSocialProvider() == SocialProvider.LOCAL) {
			throw new OAuth2AuthenticationException("이미 회원가입된 이메일입니다.");
		} else {
			return member;
		}
	}

	private Member saveMember(OAuthAttributes attributes, SocialProvider socialProvider) {
		Member member = OAuthAttributes.toEntity(socialProvider, attributes.getOAuth2MemberInfo());
		return memberRepository.save(member);
	}
}
