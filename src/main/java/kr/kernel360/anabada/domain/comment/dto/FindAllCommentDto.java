package kr.kernel360.anabada.domain.comment.dto;

import kr.kernel360.anabada.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link kr.kernel360.anabada.domain.comment.entity.Comment}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
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