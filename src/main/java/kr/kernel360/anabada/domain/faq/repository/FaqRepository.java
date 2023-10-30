package kr.kernel360.anabada.domain.faq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kernel360.anabada.domain.faq.dto.FindAllFaqDto;
import kr.kernel360.anabada.domain.faq.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq,Long> {

	@Query("select new kr.kernel360.anabada.domain.faq.dto.FindAllFaqDto("
		+ "f.id, f.title, m.nickname, f.createDate) "
		+ "   from Faq f "
		+ "  inner join f.member m")
	List<FindAllFaqDto> findFaqs();
}
