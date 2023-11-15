package kr.kernel360.anabada.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	Optional<Member> findByEmail(String email);

	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);
}
