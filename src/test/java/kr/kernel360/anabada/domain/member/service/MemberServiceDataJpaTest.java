package kr.kernel360.anabada.domain.member.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;

@DisplayName("서비스 DataJpa 테스트 - 회원")
@SpringBootTest
@Transactional
class MemberServiceDataJpaTest {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp(){
		memberRepository.deleteAll();
	}

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
		String originalEmail = "maryland@naver.com";
		String originalBirth = "2000-01-12";

		UpdateMemberRequest request = UpdateMemberRequest.builder()
			.id(1L)
			.email("awdawdaw@naver.com")
			.nickname("idontwanttodothis")
			.password("daw12g31dad1")
			.gender("F")
			.birth("1999-10-12")
			.build();

		//when
		Member member = memberRepository.save(memberInit());
		UpdateMemberResponse response = memberService.update(request);

		//then
		assertThat(response.getEmail()).isEqualTo(request.getEmail());
		assertThat(response.getBirth()).isEqualTo(request.getBirth());
	}
}
