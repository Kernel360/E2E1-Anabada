package kr.kernel360.anabada.domain.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryResponse;
import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public CreateCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
		 Category category = categoryRepository.save(CreateCategoryRequest.toEntity(createCategoryRequest));
		 return CreateCategoryResponse.of(category);
	}

}
