package kr.kernel360.anabada.domain.faq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.faq.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq,Long> {

}
