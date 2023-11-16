package kr.kernel360.anabada.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.global.utils.DateParser;
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
public class FindCommentDto {
	@ApiModelProperty(value = "댓글 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "댓글 내용", example = "어디서 만날까요?")
	private String content;

	@ApiModelProperty(value = "댓글 작성자", example = "가을전어김허수")
	private String memberNickname;

	@ApiModelProperty(value = "댓글 작성일", example = "2023-10-11")
	private String createdDate;


	public static FindCommentDto of(Comment comment) {
		return FindCommentDto.builder()
			.id(comment.getId())
			.content(comment.getContent())
			.memberNickname(comment.getMember().getNickname())
			.createdDate(DateParser.dateTimeToString(comment.getCreatedDate()))
			.build();
	}
}