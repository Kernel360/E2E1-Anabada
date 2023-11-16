package kr.kernel360.anabada.domain.comment.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
	@ApiModelProperty(value = "댓글 내용", example = "예약될까요?")
	private String content;

	public static Comment toEntity(CreateCommentRequest createCommentRequest, Member member, Trade trade){
		return Comment.builder()
			.member(member)
			.trade(trade)
			.content(createCommentRequest.content)
			.build();
	}
}
