package kr.kernel360.anabada.domain.comment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.comment.dto.CreateCommentRequest;
import kr.kernel360.anabada.domain.comment.dto.FindAllCommentResponse;
import kr.kernel360.anabada.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping("v1/trades/{tradeId}/comments")
	public ResponseEntity<Long> create(@PathVariable Long tradeId, @RequestBody CreateCommentRequest createCommentRequest) {
		Long savedCommentId = commentService.create(createCommentRequest, tradeId);

		return ResponseEntity.ok(savedCommentId);
	}

	@GetMapping("v1/trades/{tradeId}/comments")
	public ResponseEntity<FindAllCommentResponse> findAll(@PathVariable Long tradeId) {
		FindAllCommentResponse findAllCommentResponse = commentService.findAll(tradeId);

		return ResponseEntity.ok(findAllCommentResponse);
	}

}
