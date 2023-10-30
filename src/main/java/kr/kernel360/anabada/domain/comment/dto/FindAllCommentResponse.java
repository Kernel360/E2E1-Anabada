package kr.kernel360.anabada.domain.comment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindAllCommentResponse {
	List<FindAllCommentDto> comments;

	public static FindAllCommentResponse of(List<FindAllCommentDto> comments) {
		return FindAllCommentResponse.builder()
			.comments(comments)
			.build();
	}
}
