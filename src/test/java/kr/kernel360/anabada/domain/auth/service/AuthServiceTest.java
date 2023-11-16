package kr.kernel360.anabada.domain.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import kr.kernel360.anabada.domain.auth.dto.LoginRequest;
import kr.kernel360.anabada.domain.auth.dto.LoginResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;

public class AuthServiceTest {
	// @Test
	// @DisplayName("회원이 기본 로그인 시도시 존재하는 멤버고 소셜 회원이 아니라면, 로그인 응답을 반환한다")
	// void testAuthenticateWithLocalNormalAccess() throws Exception {
	// 	//given
	// 	LoginRequest loginRequest = LoginRequest.builder()
	// 		.email("example@a.com")
	// 		.password("hashed_password")
	// 		.build();
	//
	// 	Member loginMember = Member.builder()
	// 		.email(loginRequest.getEmail())
	// 		.socialProvider(SocialProvider.LOCAL)
	// 		.password(loginRequest.getPassword())
	// 		.build();
	//
	// 	Authentication authentication =
	// 		new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
	//
	// 	Optional<Member> optionalMember = Optional.of(loginMember);
	//
	// 	doReturn(optionalMember)
	// 		.when(memberRepository)
	// 		.findByEmail(anyString());
	//
	// 	doReturn(Boolean.TRUE)
	// 		.when(passwordEncoder)
	// 		.matches(anyString(), anyString());
	//
	// 	doThrow(new RuntimeException())
	// 		.when(authenticationManagerBuilder)
	// 		.getObject();
	//
	// 	doReturn(authentication)
	// 		.when(authenticationManager)
	// 		.authenticate(any(UsernamePasswordAuthenticationToken.class));
	//
	// 	//when
	// 	LoginResponse response = authService.authenticate(loginRequest);
	//
	// 	//then
	// 	assertThat(response.getEmail()).isEqualTo(loginRequest.getEmail());
	//
	// 	//verify
	// 	verify(memberRepository, times(1)).findByEmail(anyString());
	// }
}
