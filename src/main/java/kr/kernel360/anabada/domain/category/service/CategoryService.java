package kr.kernel360.anabada.domain.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindAllCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindCategoryResponse;
import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.category.repository.CategoryRepository;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.error.code.CategoryErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public CreateCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
		 validateNameUnique(createCategoryRequest.getName());
		 Category savedCategory = categoryRepository.save(CreateCategoryRequest.toEntity(createCategoryRequest));

		 return CreateCategoryResponse.of(savedCategory);
	}

	public FindAllCategoryResponse findAll() {
		List<Category> findCategories = categoryRepository.findAll();
		List<FindCategoryResponse> responses = findCategories.stream()
			.map(FindCategoryResponse::of)
			.toList();

		return FindAllCategoryResponse.of(responses);
	}

	public FindAllCategoryResponse findByDeletedStatusFalse() {
		List<Category> findCategories = categoryRepository.findAll();
		List<FindCategoryResponse> responses = findCategories.stream()
			.filter(c -> c.getDeletedStatus() == DeletedStatus.FALSE)
			.map(FindCategoryResponse::of)
			.toList();

		return FindAllCategoryResponse.of(responses);
	}

	@Transactional
	public Long remove(Long categoryId) {
		Category findCategory = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new BusinessException(CategoryErrorCode.NOT_FOUND_CATEGORY));
		if (findCategory.getDeletedStatus() == DeletedStatus.TRUE) {
			findCategory.activate();
			return categoryId;
		}
		categoryRepository.delete(findCategory);

		return categoryId;
	}

	public void validateNameUnique(String name) {
		if (categoryRepository.existsByName(name)) {
			throw new BusinessException(CategoryErrorCode.ALREADY_SAVED_CATEGORY);
		}
	}
}
