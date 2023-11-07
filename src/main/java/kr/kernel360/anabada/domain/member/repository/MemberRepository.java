package kr.kernel360.anabada.domain.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kernel360.anabada.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findOneWithAuthoritiesByEmail(String username);
	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);

	@Query("SELECT m.gender, COUNT(m) FROM Member m GROUP BY m.gender")
	List<Object[]> countMembersByGender();

	@Query("SELECT " +
		"CASE " +
		"  WHEN FUNCTION('DATEDIFF', CURRENT_DATE(), FUNCTION('STR_TO_DATE', CONCAT(SUBSTRING(m.birth, 1, 4), '-12-31'), '%Y-%m-%d'))/365 <= 9 THEN '10대' " +
		"  WHEN FUNCTION('DATEDIFF', CURRENT_DATE(), FUNCTION('STR_TO_DATE', CONCAT(SUBSTRING(m.birth, 1, 4), '-12-31'), '%Y-%m-%d'))/365 BETWEEN 10 AND 19 THEN '20대' " +
		"  WHEN FUNCTION('DATEDIFF', CURRENT_DATE(), FUNCTION('STR_TO_DATE', CONCAT(SUBSTRING(m.birth, 1, 4), '-12-31'), '%Y-%m-%d'))/365 BETWEEN 20 AND 29 THEN '30대' " +
		"  WHEN FUNCTION('DATEDIFF', CURRENT_DATE(), FUNCTION('STR_TO_DATE', CONCAT(SUBSTRING(m.birth, 1, 4), '-12-31'), '%Y-%m-%d'))/365 BETWEEN 30 AND 39 THEN '40대' " +
		"  WHEN FUNCTION('DATEDIFF', CURRENT_DATE(), FUNCTION('STR_TO_DATE', CONCAT(SUBSTRING(m.birth, 1, 4), '-12-31'), '%Y-%m-%d'))/365 BETWEEN 40 AND 49 THEN '50대' " +
		"  ELSE '60대 이상' " +
		"END AS ageGroup, COUNT(m.id) " +
		"FROM Member m GROUP BY ageGroup")
	List<Object[]> countMembersByAgeGroup();
}
