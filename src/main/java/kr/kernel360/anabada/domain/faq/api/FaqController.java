package kr.kernel360.anabada.domain.faq.api;

import java.net.URI;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.faq.dto.CreateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.CreateFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FaqSearchCondition;
import kr.kernel360.anabada.domain.faq.dto.FindAllFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FindFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqResponse;
import kr.kernel360.anabada.domain.faq.service.FaqService;
import lombok.RequiredArgsConstructor;

@Api(tags = "FAQ API", description = "/api/v1/faqs")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FaqController {
	private final FaqService faqService;

	@ApiOperation(value = "FAQ 생성")
	@ApiResponses({@ApiResponse(code = 201, message = "FAQ 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/faqs")
	public ResponseEntity<CreateFaqResponse> create(@RequestBody CreateFaqRequest createFaqRequest) {
		CreateFaqResponse createFaqResponse = faqService.create(createFaqRequest);
		URI url = URI.create("api/v1/faqs/" + createFaqResponse.getId());

		return ResponseEntity.created(url).body(createFaqResponse);
	}

	@ApiOperation(value = "작성자, 제목을 통해서 모든 FAQ를 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "조건에 맞는 모든 FAQ 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "page_number", required = true
		, dataType = "int", paramType = "query", defaultValue = "1")
	@GetMapping("/v1/faqs")
	public ResponseEntity<FindAllFaqResponse> findAll(FaqSearchCondition faqSearchCondition,
													  @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		Pageable pageable = PageRequest.of(pageNo<1 ? 0 : pageNo-1, 10);
		FindAllFaqResponse findAllFaqResponse = faqService.findAll(faqSearchCondition, pageable);
		
		return ResponseEntity.ok(findAllFaqResponse);
	}

	@ApiOperation(value = "단일 FAQ 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "단일 FAQ 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 FAQ 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "faqId", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@GetMapping("/v1/faqs/{faqId}")
	public ResponseEntity<FindFaqResponse> find(@PathVariable Long faqId) {
		FindFaqResponse findFaqResponse = faqService.find(faqId);

		return ResponseEntity.ok(findFaqResponse);
	}

	@ApiOperation(value = "FAQ 수정")
	@ApiResponses({@ApiResponse(code = 200, message = "FAQ 수정 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 FAQ 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PutMapping("/v1/faqs")
	public ResponseEntity<UpdateFaqResponse> update(@RequestBody UpdateFaqRequest updateFaqRequest) {;
		UpdateFaqResponse updateFaqResponse = faqService.update(updateFaqRequest);

		return ResponseEntity.ok(updateFaqResponse);
	}

	@ApiOperation(value = "FAQ 삭제")
	@ApiResponses({@ApiResponse(code = 200, message = "FAQ 삭제 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 FAQ 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "faqId", required = true
		, dataType = "int", paramType = "query", defaultValue = "1")
	@DeleteMapping("/v1/faqs/{faqId}")
	public ResponseEntity<Long> remove(@PathVariable Long faqId) {
		Long deletedFaqId = faqService.delete(faqId);

		return ResponseEntity.ok().body(deletedFaqId);
	}
}
