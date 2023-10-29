package kr.kernel360.anabada.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
