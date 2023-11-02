package kr.kernel360.anabada.domain.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;

@DisplayName("회원 서비스 단위 테스트")
@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	private Member memberInit() {
		return CreateMemberRequest.toEntity(
			CreateMemberRequest.builder()
				.email("awdawd@naver.com")
				.nickname("JohnDoe")
				.password("1234124")
				.gender("M")
				.birth("1991-11-11")
				.build());
	}

	@Test
	@DisplayName("회원 정보를 입력하면, 회원 정보를 수정하고 회원 수정 응답을 반환한다.")
	void 회원정보_수정() throws Exception {
		//given
		String newEmail = "홍길동@naver.com";
		String newBirth = "2020-01-12";

		Member member = memberInit();

		memberRepository.save(member);

		UpdateMemberRequest request = UpdateMemberRequest.builder()
			.id(member.getId())
			.email(newEmail)
			.nickname(member.getNickname())
			.password(member.getPassword())
			.gender(member.getGender())
			.birth(newBirth)
			.build();

		//when
		UpdateMemberResponse response = memberService.update(request);

		//then
		assertThat(response.getEmail()).isEqualTo(newEmail);
		assertThat(response.getBirth()).isEqualTo(newBirth);
	}

	@Test
	@DisplayName("회원을 저장하고 조회하면, 동일한 회원을 반환한다.")
	void 회원_조회() throws Exception {
		//given
		Member member = memberRepository.save(memberInit());

		//when
		FindMemberResponse findMemberResponse = memberService.find(member.getId());

		//then
		assertThat(findMemberResponse.getNickname()).isEqualTo(member.getNickname());
	}

	@Test
	@DisplayName("존재하지 않는 회원을 조회하면, IllegalArgumentException을 반환한다.")
	void 비정상_회원_조회() throws Exception {
		//given
		Member member = memberRepository.save(memberInit());

		//when
		Throwable exception = assertThrows(IllegalArgumentException.class, () ->
			memberService.find(member.getId() + 1));

		//then
		assertThat(exception).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("회원을 저장하고 삭제하면, 해당 회원의 회원 탈퇴가 TRUE로 변경된다.")
	void 회원_삭제() throws Exception {
		//given
		Member member = memberRepository.save(memberInit());

		//when
		Long removedId = memberService.remove(member.getId());

		//then
		assertThat(removedId).isEqualTo(member.getId());
	}

	@Test
	@DisplayName("존재하지 않는 회원을 삭제하면, IllegalArgumentException을 반환한다.")
	void 비정상_회원_삭제() throws Exception {
		//given
		Member member = memberRepository.save(memberInit());

		//when
		Throwable exception = assertThrows(IllegalArgumentException.class, () ->
			memberService.remove(member.getId() + 1));

		//then
		assertThat(exception).isInstanceOf(IllegalArgumentException.class);
	}
}
