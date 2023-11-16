package kr.kernel360.anabada.domain.category.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindAllCategoryResponse;
import kr.kernel360.anabada.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Api(tags = "카테고리 API", description = "/api/v1/categories")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@ApiOperation(value = "카테고리 생성")
	@ApiResponses({@ApiResponse(code = 201, message = "카테고리 추가 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 409, message = "동일한 카테고리가 존재합니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/categories")
	public ResponseEntity<CreateCategoryResponse> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
		CreateCategoryResponse createCategoryResponse = categoryService.create(createCategoryRequest);
		URI url = URI.create("api/v1/categories/" + createCategoryResponse.getId());

		return ResponseEntity.created(url).body(createCategoryResponse);
	}

	@ApiOperation(value = "모든 카테고리 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "모든 카테고리 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/v1/categories")
	public ResponseEntity<FindAllCategoryResponse> findAll(){
		FindAllCategoryResponse findAllCategoryResponse = categoryService.findAll();

		return ResponseEntity.ok(findAllCategoryResponse);
	}

	@ApiOperation(value = "모든 활성화 카테고리 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "모든 활성화 카테고리 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/v1/categories/active")
	public ResponseEntity<FindAllCategoryResponse> findAllByDeletedStatusFalse(){
		FindAllCategoryResponse findAllCategoryResponse = categoryService.findByDeletedStatusFalse();

		return ResponseEntity.ok(findAllCategoryResponse);
	}

	@ApiOperation(value = "단일 카테고리 삭제")
	@ApiResponses({@ApiResponse(code = 200, message = "단일 카테고리 삭제 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "categoryId", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@DeleteMapping("v1/categories/{id}")
	public ResponseEntity<Long> remove(@PathVariable Long id) {
		categoryService.remove(id);

		return ResponseEntity.ok(id);
	}
}
