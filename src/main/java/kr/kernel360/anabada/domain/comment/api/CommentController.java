package kr.kernel360.anabada.domain.comment.api;

import org.springframework.http.ResponseEntity;
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
import kr.kernel360.anabada.domain.comment.dto.CreateCommentRequest;
import kr.kernel360.anabada.domain.comment.dto.FindAllCommentResponse;
import kr.kernel360.anabada.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

@Api(tags = "댓글 API", description = "/api/v1/comments")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@ApiOperation(value = "댓글 생성")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 추가 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원입니다. \t\n 교환 정보를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "tradeId", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@PostMapping("v1/trades/{tradeId}/comments")
	public ResponseEntity<Long> create(@PathVariable Long tradeId, @RequestBody CreateCommentRequest createCommentRequest) {
		Long savedCommentId = commentService.create(createCommentRequest, tradeId);

		return ResponseEntity.ok(savedCommentId);
	}

	@ApiOperation(value = "모든 댓글 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "모든 댓글 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원입니다. \t\n 교환 정보를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "tradeId", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@GetMapping("v1/trades/{tradeId}/comments")
	public ResponseEntity<FindAllCommentResponse> findAll(@PathVariable Long tradeId) {
		FindAllCommentResponse findAllCommentResponse = commentService.findAll(tradeId);

		return ResponseEntity.ok(findAllCommentResponse);
	}

}
