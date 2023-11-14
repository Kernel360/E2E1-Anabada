package kr.kernel360.anabada.domain.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);

	@Query("SELECT m.gender, COUNT(m) FROM Member m GROUP BY m.gender")
	List<Object[]> countMembersByGender();

	//회원이 많아질수록 성능이 떨어집니다. 계산을 매번 쿼리를 날리는 것보다 미리 나이값을 구해놓고 가져오는것도 방법일 듯합니다. 통계 테이블을 따로 관리하는 것도 좋은 방법일것 같아요.
	// SQL 쿼리가 무거우면 안된다. 인덱스가 많은 경우에서 테이블이 늘어나면 데이터 베이스 용량이 늘어나니까 이런 것도 고려해보면 좋을것 같아요.

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
