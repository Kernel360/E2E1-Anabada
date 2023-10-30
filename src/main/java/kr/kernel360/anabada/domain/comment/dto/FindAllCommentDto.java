package kr.kernel360.anabada.domain.comment.dto;

import kr.kernel360.anabada.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for {@link kr.kernel360.anabada.domain.comment.entity.Comment}
 */
@AllArgsConstructor
@Builder
@Getter
public class FindAllCommentDto {
	private Long id;
	private String content;
	private String memberNickname;


	public static FindAllCommentDto of(Comment comment) {
		return FindAllCommentDto.builder()
			.id(comment.getId())
			.content(comment.getContent())
			.memberNickname(comment.getMember().getNickname())
			.build();
	}
}