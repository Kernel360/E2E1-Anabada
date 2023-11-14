package kr.kernel360.anabada.domain.oauth2.handler;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import kr.kernel360.anabada.domain.auth.dto.TokenDto;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.oauth2.CustomOAuth2User;
import kr.kernel360.anabada.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final MemberRepository memberRepository;

	private final TokenProvider tokenProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

		Member member = memberRepository
			.findByEmail(oAuth2User.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		GrantedAuthority authority = new SimpleGrantedAuthority(member.getAuthorities());

		Authentication serviceAuthentication = new UsernamePasswordAuthenticationToken(member.getEmail(),
			member.getPassword(), Collections.singletonList(authority));

		TokenDto tokenDto = tokenProvider.createToken(serviceAuthentication);

		String targetUrl = UriComponentsBuilder.fromUriString("/")
			.queryParam("accessToken", tokenDto.getAccessToken())
			.queryParam("refreshToken", tokenDto.getRefreshToken())
			.build().toUriString();

		response.sendRedirect(targetUrl);
	}
}
