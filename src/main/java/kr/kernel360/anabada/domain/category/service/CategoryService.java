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
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public CreateCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
		 validateNameUnique(createCategoryRequest.getName());
		 Category category = categoryRepository.save(CreateCategoryRequest.toEntity(createCategoryRequest));
		 return CreateCategoryResponse.of(category);
	}

	public FindAllCategoryResponse findAll() {
		List<Category> categories = categoryRepository.findAll();
		List<FindCategoryResponse> responses = categories.stream().map(FindCategoryResponse::of).toList();

		return FindAllCategoryResponse.of(responses);
	}

	public FindAllCategoryResponse findByDeletedStatusFalse() {
		List<Category> categories = categoryRepository.findAll();
		List<FindCategoryResponse> responses = categories.stream()
			.filter(c -> c.getDeletedStatus() == DeletedStatus.FALSE)
			.map(FindCategoryResponse::of)
			.toList();

		return FindAllCategoryResponse.of(responses);
	}

	@Transactional
	public void remove(Long id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다"));
		if (category.getDeletedStatus().equals(DeletedStatus.FALSE)) {
			categoryRepository.delete(category);
		} else {
			category.activate();
		}
	}

	public void validateNameUnique(String name) {
		if (categoryRepository.existsByName(name)) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("사용중인 카테고리명 입니다.");
		}
	}
}
