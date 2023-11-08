package kr.kernel360.anabada.domain.faq.dto;

import kr.kernel360.anabada.domain.faq.entity.Faq;
import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFaqRequest {
	private String title;

	private String content;

	public static Faq toEntity(CreateFaqRequest createFaqRequest, Member member) {
		return Faq.builder()
			.title(createFaqRequest.title)
			.content(createFaqRequest.content)
			.member(member)
			.build();
	}

}
