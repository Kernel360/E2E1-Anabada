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
		 Category category = categoryRepository.save(CreateCategoryRequest.toEntity(createCategoryRequest));
		 return CreateCategoryResponse.of(category);
	}

	public FindCategoryResponse find(Long id) {
		Category category = findCategoryById(id);
		return FindCategoryResponse.of(category);
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
		Category category = findCategoryById(id);
		if (category.getDeletedStatus().equals(DeletedStatus.FALSE)) {
			categoryRepository.delete(category);
		} else {
			category.activate();
		}
	}

	public Category findCategoryById(Long id){
		return categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다"));
	}
}
