package kr.kernel360.anabada.domain.category.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindAllCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindCategoryResponse;
import kr.kernel360.anabada.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@PostMapping("/v1/categories")
	public ResponseEntity<CreateCategoryResponse> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
		CreateCategoryResponse createCategoryResponse = categoryService.create(createCategoryRequest);
		URI url = URI.create("api/v1/categories/" + createCategoryResponse.getId());
		return ResponseEntity.created(url).body(createCategoryResponse);
	}

	@GetMapping("/v1/categories")
	public ResponseEntity<FindAllCategoryResponse> findAll(){
		FindAllCategoryResponse findAllCategoryResponse = categoryService.findAll();
		return ResponseEntity.ok(findAllCategoryResponse);
	}

	@GetMapping("/v1/categories/{id}")
	public ResponseEntity<FindCategoryResponse> find(@PathVariable Long id) {
		FindCategoryResponse findCategoryResponse = categoryService.find(id);
		return ResponseEntity.ok(findCategoryResponse);
	}

}
