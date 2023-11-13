package kr.kernel360.anabada.domain.faq.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.kernel360.anabada.domain.faq.dto.FaqSearchCondition;
import kr.kernel360.anabada.domain.faq.dto.FindFaqDto;

public interface FaqRepositoryCustom {
	Page<FindFaqDto> findFaqs(FaqSearchCondition faqSearchCondition, Pageable pageable);

	FindFaqDto findFaq(Long faqId);
}
