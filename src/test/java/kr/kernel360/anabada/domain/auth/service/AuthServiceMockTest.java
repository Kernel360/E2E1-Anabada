package kr.kernel360.anabada.domain.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.kernel360.anabada.domain.auth.dto.LoginRequest;
import kr.kernel360.anabada.domain.auth.dto.SignUpRequest;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
import kr.kernel360.anabada.global.error.code.AuthErrorCode;
import kr.kernel360.anabada.global.error.code.MemberErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import kr.kernel360.anabada.global.jwt.TokenProvider;

@DisplayName("인증 서비스 단위 mock 테스트")
@ExtendWith(MockitoExtension.class)
public class AuthServiceMockTest {
	@InjectMocks
	private AuthService authService;
	@Mock
	private MemberRepository memberRepository;
	@Mock
	private TokenProvider tokenProvider;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	@BeforeEach
	void setUp() {
		new AuthService(passwordEncoder, tokenProvider, authenticationManagerBuilder, memberRepository);
	}

	@AfterEach
	void destroy() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 회원의 인덱스를 반환한다.")
	void testSignUp() throws Exception {

		//given
		Long memberId = 1L;

		SignUpRequest signUpRequest = SignUpRequest.builder()
			.email("example@naver.com")
			.nickname("example")
			.password("password")
			.gender("M")
			.birth("1999-10-11")
			.build();

		Member signUpMember = Member.builder()
			.email(signUpRequest.getEmail())
			.nickname(signUpRequest.getNickname())
			.build();

		Optional<Member> optionalMember = Optional.of(signUpMember);

		doReturn(optionalMember)
			.when(memberRepository)
			.findById(anyLong());

		doReturn(signUpMember)
			.when(memberRepository)
			.save(any(Member.class));

		//when
		Long id = authService.signUp(signUpRequest);
		Member foundMember = memberRepository.findById(memberId).orElseThrow(
			() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER)
		);

		//then
		assertThat(foundMember.getEmail()).isEqualTo(signUpRequest.getEmail());
		assertThat(foundMember.getNickname()).isEqualTo(signUpRequest.getNickname());

		//verify
		verify(memberRepository, times(1)).findById(anyLong());
		verify(memberRepository, times(1)).save(any(Member.class));
		verify(passwordEncoder, times(1)).encode(anyString());
	}

	@Test
	@DisplayName("회원 가입 요청에 필수 정보들이 존재하는지 확인한다")
	void testSignUpRequestValidationIfRequiredInfoIncluded() throws Exception {
		//given
		SignUpRequest signUpRequest = SignUpRequest.builder()
			.email("example@naver.com")
			.nickname(null)
			.password(null)
			.gender("M")
			.birth("1999-10-11")
			.build();

		//when
		Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

		//then
		assertEquals(2, violations.size());
	}

	@Test
	@DisplayName("회원 가입 요청에 이메일 형식이 올바른지 확인한다")
	void testValidateMemberEmail() throws Exception {
		//given
		SignUpRequest signUpRequest = SignUpRequest.builder()
			.email("example@")
			.nickname("daiiwdawdo")
			.password("123412512")
			.gender("M")
			.birth("1999-10-11")
			.build();

		//when
		Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

		//then
		assertEquals(1, violations.size());
	}

	@Test
	@DisplayName("존재하지 않는 회원 정보로 로그인을 시도하면 AuthErrorCode.INVALID_ACCOUNT를 반환한다.")
	void testAuthenticateMemberDoesNotExist() throws Exception {
	    //given
		Member member = Member.builder()
			.email("adawd@naver.com")
			.socialProvider(SocialProvider.KAKAO)
			.password("12134421")
			.build();

		LoginRequest loginRequest = LoginRequest.builder()
			.email(member.getEmail())
			.password(member.getPassword())
			.build();

		doThrow(new BusinessException(AuthErrorCode.INVALID_ACCOUNT))
			.when(memberRepository)
			.findByEmail(anyString());

	    //when
		BusinessException businessException = assertThrows(BusinessException.class, ()
			-> authService.authenticate(loginRequest));

		//then
		assertThat(businessException).isInstanceOf(BusinessException.class);
		assertEquals(AuthErrorCode.INVALID_ACCOUNT, businessException.getErrorCode());

		//verify
		verify(memberRepository, times(1)).findByEmail(anyString());
	}

	@Test
	@DisplayName("이미 소셜 로그인으로 가입한 회원이 기본 로그인을 시도하면 AuthErrorCode.ALREADY_SOCIAL_LOGIN를 반환한다.")
	void testAuthenticateIsAlreadySocialLogin() throws Exception {
	    //given
		Member member = Member.builder()
			.email("adawd@naver.com")
			.socialProvider(SocialProvider.KAKAO)
			.password("12134421")
			.build();

		LoginRequest loginRequest = LoginRequest.builder()
			.email(member.getEmail())
			.password(member.getPassword())
			.build();

		Optional<Member> optionalMember = Optional.of(member);

		doReturn(optionalMember)
			.when(memberRepository)
			.findByEmail(anyString());

		//when
		BusinessException businessException = assertThrows(BusinessException.class, ()
			-> authService.authenticate(loginRequest));

	    //then
		assertThat(businessException).isInstanceOf(BusinessException.class);
		assertEquals(AuthErrorCode.ALREADY_SOCIAL_LOGIN, businessException.getErrorCode());

		//verify
		verify(memberRepository, times(1)).findByEmail(anyString());
	}

	@Test
	@DisplayName("회원이 기본 로그인을 시도하고 비밀번호 불일치 시, AuthErrorCode.INVALID_PASSWORD를 반환한다")
	void testAuthenticateCheckPassword() throws Exception {
	    //given
		Member member = Member.builder()
			.email("adawd@naver.com")
			.socialProvider(SocialProvider.LOCAL)
			.password("12134421")
			.build();

		LoginRequest loginRequest = LoginRequest.builder()
			.email(member.getEmail())
			.password("1241awgaww")
			.build();

		Optional<Member> optionalMember = Optional.of(member);

		doReturn(optionalMember)
			.when(memberRepository)
			.findByEmail(anyString());

	    //when
		BusinessException businessException = assertThrows(BusinessException.class, ()
			-> authService.authenticate(loginRequest));

		//then
		assertThat(businessException).isInstanceOf(BusinessException.class);
		assertEquals(AuthErrorCode.INVALID_PASSWORD, businessException.getErrorCode());

		//verify
		verify(memberRepository, times(1)).findByEmail(anyString());
	}

	@Test
	@DisplayName("회원 가입시 동일한 이메일이 존재하면 AuthErrorCode.ALREADY_SAVED_EMAIL을 반환한다.")
	void testIsEmailUnique() throws Exception {
	    //given
		String email = "idontknow@naver.com";

		doReturn(true)
			.when(memberRepository)
			.existsByEmail(anyString());

	    //when
		BusinessException businessException = assertThrows(BusinessException.class, ()
			-> authService.isEmailUnique(email));

	    //then
		assertThat(businessException).isInstanceOf(BusinessException.class);
		assertEquals(AuthErrorCode.ALREADY_SAVED_EMAIL, businessException.getErrorCode());

		//verify
		verify(memberRepository, times(1)).existsByEmail(anyString());
	}

	@Test
	@DisplayName("회원 가입시 동일한 닉네임이 존재하면 AuthErrorCode.ALREADY_SAVED_NICKNAME을 반환한다.")
	void testIsNicknameUnique() throws Exception {
	    //given
		String nickname = "idontknow";

		doReturn(true)
			.when(memberRepository)
			.existsByNickname(anyString());

	    //when
		BusinessException businessException = assertThrows(BusinessException.class, ()
			-> authService.isNicknameUnique(nickname));

	    //then
		assertThat(businessException).isInstanceOf(BusinessException.class);
		assertEquals(AuthErrorCode.ALREADY_SAVED_NICKNAME, businessException.getErrorCode());

		//verify
		verify(memberRepository, times(1)).existsByNickname(anyString());
	}
}