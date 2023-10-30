package kr.kernel360.anabada.domain.comment.dto;

import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
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
	private Long memberId;
	private String content;

	public static Comment toEntity(CreateCommentRequest createCommentRequest, Member member, Trade trade){
		return Comment.builder()
			.member(member)
			.trade(trade)
			.content(createCommentRequest.content)
			.build();
	}
}
