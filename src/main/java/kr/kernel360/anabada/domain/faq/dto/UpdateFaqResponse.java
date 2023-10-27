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
public class UpdateFaqResponse {
	private Long id;

	private String title;

	private String content;

	public static UpdateFaqResponse of(Faq faq) {
		return UpdateFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
	}
}
