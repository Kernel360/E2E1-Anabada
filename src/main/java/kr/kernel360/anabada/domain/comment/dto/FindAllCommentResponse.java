package kr.kernel360.anabada.domain.comment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindAllCommentResponse {
	@JsonProperty("comments")
	List<FindCommentDto> comments;

	public static FindAllCommentResponse of(List<FindCommentDto> comments) {
		return FindAllCommentResponse.builder()
			.comments(comments)
			.build();
	}
}
