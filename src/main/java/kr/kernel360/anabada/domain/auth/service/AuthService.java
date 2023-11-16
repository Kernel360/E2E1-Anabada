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
import kr.kernel360.anabada.global.error.code.AuthErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
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
			.orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_ACCOUNT));
		validateLogin(loginRequest, findMember);
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject()
			.authenticate(authenticationToken);
		TokenDto token = tokenProvider.createToken(authentication);

		return LoginResponse.of(findMember.getEmail(), authentication.getAuthorities(), token);
	}

	@Transactional
	public Long signUp(SignUpRequest signUpRequest) {
		signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		signUpRequest.setAgeGroup(AgeGroupParser.birthToAgeGroup(signUpRequest.getBirth()));
		Member savedMember = memberRepository.save(SignUpRequest.toEntity(signUpRequest));

		return savedMember.getId();
	}

	public TokenDto reissueAccessToken(TokenDto requestTokenDto) {
		return tokenProvider.reissueToken(requestTokenDto);
	}

	public void logout(String refreshToken) {
		tokenProvider.removeRedisRefreshToken(refreshToken);
	}

	private void isAlreadySocialLogin(Member findMember) {
		if (findMember.getSocialProvider() != SocialProvider.LOCAL) {
			throw new BusinessException(AuthErrorCode.ALREADY_SOCIAL_LOGIN);
		}
	}

	private void validateLogin(LoginRequest loginRequest, Member member) {
		isAlreadySocialLogin(member);
		checkPassword(loginRequest.getPassword(), member.getPassword());
	}

	private void checkPassword(String loginPassword, String encodeUserPassword) {
		if (!passwordEncoder.matches(loginPassword, encodeUserPassword)) {
			throw new BusinessException(AuthErrorCode.INVALID_PASSWORD);
		}
	}

	public void isEmailUnique(String email) {
		if (memberRepository.existsByEmail(email)) {
			throw new BusinessException(AuthErrorCode.ALREADY_SAVED_EMAIL);
		}
	}

	public void isNicknameUnique(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			throw new BusinessException(AuthErrorCode.ALREADY_SAVED_NICKNAME);
		}
	}
}
