package kr.kernel360.anabada.domain.oauth2.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.kernel360.anabada.domain.auth.dto.TokenDto;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.oauth2.CustomOAuth2User;
import kr.kernel360.anabada.global.commons.domain.PrincipalDetails;
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
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
			authenticateUser(oAuth2User);
			response.sendRedirect("/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void authenticateUser(CustomOAuth2User oAuth2User) {
		Member member = memberRepository.findOneWithAuthoritiesByEmail(oAuth2User.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
		PrincipalDetails principalDetails = new PrincipalDetails(member, oAuth2User.getAttributes());
		Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null
			, principalDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
