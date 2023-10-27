package kr.kernel360.anabada.domain.faq.dto;

import kr.kernel360.anabada.domain.faq.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFaqResponse {
	private Long id;

	private String title;

	private String content;

	public static CreateFaqResponse of(Faq faq) {
		return CreateFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
	}

}
