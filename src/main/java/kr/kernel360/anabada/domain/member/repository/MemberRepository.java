package kr.kernel360.anabada.domain.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kernel360.anabada.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	Optional<Member> findByEmail(String email);

	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);

	@Query("SELECT m.gender, COUNT(m) FROM Member m GROUP BY m.gender")
	List<Object[]> countMembersByGender();
}
