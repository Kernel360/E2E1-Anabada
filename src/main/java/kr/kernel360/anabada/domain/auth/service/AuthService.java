package kr.kernel360.anabada.domain.auth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.auth.dto.LoginRequest;
import kr.kernel360.anabada.domain.auth.dto.LoginResponse;
import kr.kernel360.anabada.domain.auth.dto.SignUpRequest;
import kr.kernel360.anabada.domain.auth.dto.TokenDto;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
import kr.kernel360.anabada.global.jwt.TokenProvider;
import kr.kernel360.anabada.global.utils.AgeGroupParser;
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
		Member findMember = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));

		if (findMember.getSocialProvider() != SocialProvider.LOCAL) {
			throw new IllegalArgumentException("소셜로 가입한 회원입니다. 소셜로 로그인 해주세요.");
		}

		validateLogin(loginRequest, findMember);

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject()
			.authenticate(authenticationToken);

		TokenDto token = tokenProvider.createToken(authentication);

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

	public void isEmailUnique(String email) {
		if (memberRepository.existsByEmail(email)) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("사용중인 이메일입니다.");
		}
	}

	public void isNickname(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("사용중인 닉네임입니다.");
		}
	}

	@Transactional
	public Long signUp(SignUpRequest signUpRequest) {
		signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		signUpRequest.setAgeGroup(AgeGroupParser.birthToAgeGroup(signUpRequest.getBirth()));
		Member member = memberRepository.save(SignUpRequest.toEntity(signUpRequest));
		return member.getId();
	}

	public TokenDto reissueAccessToken(TokenDto requestTokenDto) {
		return tokenProvider.reissueToken(requestTokenDto);
	}

	public void logout(String refreshToken) {
		tokenProvider.removeRedisRefreshToken(refreshToken);
	}
}
