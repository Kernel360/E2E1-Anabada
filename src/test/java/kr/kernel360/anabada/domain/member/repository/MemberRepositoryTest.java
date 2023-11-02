package kr.kernel360.anabada.domain.member.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kr.kernel360.anabada.domain.member.entity.Member;

@DisplayName("회원 리포지토리 단위 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("회원 객체가 만들어지면, repository에 저장된다")
	void 회원_저장() throws Exception {
	    //given
		Member member = createMember();

		//when
		Member savedMember = memberRepository.save(member);

		//then
		assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
		assertThat(member.getNickname()).isEqualTo(savedMember.getNickname());
	}

	@Test
	@DisplayName("존재하는 회원을 조회하면, 회원을 반환한다.")
	void 회원_조회하기() throws Exception {
	    //given
		Member member = createMember();

		Member savedMember = memberRepository.save(member);

	    //when
		Member foundMember = memberRepository.findById(savedMember.getId())
			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

		//then
		assertThat(foundMember.getId()).isEqualTo(savedMember.getId());
	}

	private static Member createMember() {
		Member member = Member.builder()
			.email("ad2d@naver.com")
			.nickname("iwanttogohome")
			.password("123412")
			.authorities("USER_ROLE")
			.gender("M")
			.birth("1991-10-10")
			.socialProvider("N")
			.accountStatus(true)
			.build();
		return member;
	}
}
