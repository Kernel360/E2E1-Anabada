package kr.kernel360.anabada.domain.faq.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.kernel360.anabada.domain.faq.dto.FindFaqDto;
import kr.kernel360.anabada.domain.faq.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq,Long> {

	@Query("select new kr.kernel360.anabada.domain.faq.dto.FindFaqDto("
		+ "f.id, f.title, m.nickname, f.createdDate) "
		+ "   from Faq f "
		+ "  inner join f.member m"
		+ "  order by f.createdDate desc")
	List<FindFaqDto> findFaqs();

	@Query("select new kr.kernel360.anabada.domain.faq.dto.FindFaqDto("
		+ "f.id, f.title, f.content, m.nickname, f.createdDate) "
		+ "   from Faq f "
		+ "  inner join f.member m"
		+ "  where f.id = :faqId")
	Optional<FindFaqDto> findFaq(@Param("faqId") Long faqId);
}
