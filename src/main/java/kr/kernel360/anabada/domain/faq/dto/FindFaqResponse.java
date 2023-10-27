package kr.kernel360.anabada.domain.faq.dto;

import kr.kernel360.anabada.domain.faq.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindFaqResponse {
	private Long id;

	private String title;

	private String content;

	public static FindFaqResponse of(Faq faq) {
		return FindFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
	}
}
