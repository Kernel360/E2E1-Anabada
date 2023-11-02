package kr.kernel360.anabada.domain.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;

@DisplayName("회원 서비스 mock 단위 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceMockTest {
	@InjectMocks
	private MemberService memberService;

	@Mock
	private MemberRepository memberRepository;

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	@AfterEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 회원 가입 응답을 반환한다.")
	void 회원가입_후_저장() throws Exception {
	    //given
		CreateMemberRequest request = CreateMemberRequest.builder()
			.email("2agwad@naver.com")
			.nickname("email")
			.password("1234")
			.gender("M")
			.birth("1910-12-11")
			.build();

		doReturn(new Member(request.getEmail()
			, request.getNickname()
			, request.getPassword()
			, request.getGender()
			, request.getBirth()))
			.when(memberRepository)
			.save(any(Member.class));

	    //when
		CreateMemberResponse member = memberService.create(request);

	    //then
		assertThat(member.getEmail()).isEqualTo(request.getEmail());
		assertThat(member.getNickname()).isEqualTo(request.getNickname());

		//verify
		verify(memberRepository, times(1)).save(any(Member.class));
	}

	@Test
	@DisplayName("존재하지 않는 회원 ID를 검색하면, IllegalArgumentException을 반환한다.")
	void 회원조회() throws Exception {
	    //given
	    Long id = 1L;

	    //when
		Throwable exception = assertThrows(IllegalArgumentException.class, () ->
			memberService.find(id));

		//then
		assertThat(exception).isInstanceOf(IllegalArgumentException.class);

		//verify
		verify(memberRepository, times(1)).findById(id);
	}

	@Test
	@DisplayName("회원 가입을 요청하면, 필수 정보들이 존재하는지 확인한다")
	void 회원가입_필수정보_확인() throws Exception {
	    //given
		CreateMemberRequest request = CreateMemberRequest.builder()
			.email("2agwad@naver.com")
			.nickname(null)
			.password(null)
			.gender("M")
			.birth("1910-12-11")
			.build();

	    //when
		Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(request);

	    //then
		assertEquals(2, violations.size());
	}

	@Test
	@DisplayName("회원 가입을 요청하면, 이메일 형식이 올바른지 확인한다")
	void 회원가입_이메일_유효성_검사() throws Exception {
	    //given
		CreateMemberRequest request = CreateMemberRequest.builder()
			.email("@ddnav")
			.nickname("der22df")
			.password("ddf2332fdf")
			.gender("M")
			.birth("1910-12-11")
			.build();

		//when
		Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(request);
		System.out.println(violations);

		//then
		assertEquals(1, violations.size());
	}
}