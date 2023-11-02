package kr.kernel360.anabada.domain.auth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.auth.dto.LoginRequest;
import kr.kernel360.anabada.domain.auth.dto.LoginResponse;
import kr.kernel360.anabada.domain.auth.dto.TokenResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final MemberRepository memberRepository;

	public LoginResponse authenticate(LoginRequest loginRequest) {
		Member findMember = memberRepository.findOneWithAuthoritiesAByEmail(loginRequest.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));

		validateLogin(loginRequest, findMember);

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject()
			.authenticate(authenticationToken);

		TokenResponse token = tokenProvider.createToken(authentication);
		System.out.println("token >>>> "+token);

		return LoginResponse.of(findMember.getEmail(), authentication.getAuthorities(), token);
	}

	private void validateLogin(LoginRequest loginRequest, Member member) {
		checkPassword(loginRequest.getPassword(), member.getPassword());
	}

	private void checkPassword(String loginPassword, String encodeUserPassword) {
		if (!passwordEncoder.matches(loginPassword, encodeUserPassword)) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("비밀번호가 다릅니다.");
		}
	}
}
