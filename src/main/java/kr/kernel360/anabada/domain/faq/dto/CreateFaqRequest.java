package kr.kernel360.anabada.domain.faq.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.faq.entity.Faq;
import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFaqRequest {
	@ApiModelProperty(value = "FAQ 제목", example = "제가 등록한 교환이 보이지 않아요."
		, required = true)
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "페이지를 새로고침 해주세요."
		, required = true)
	private String content;

	public static Faq toEntity(CreateFaqRequest createFaqRequest, Member member) {
		return Faq.builder()
			.title(createFaqRequest.title)
			.content(createFaqRequest.content)
			.member(member)
			.build();
	}

}
