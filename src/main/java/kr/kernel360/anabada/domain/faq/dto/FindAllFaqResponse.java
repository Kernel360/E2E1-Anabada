package kr.kernel360.anabada.domain.faq.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllFaqResponse {
	private List<FindFaqDto> faqs;

	public static FindAllFaqResponse of(List<FindFaqDto> faqs) {
		return FindAllFaqResponse.builder()
			.faqs(faqs)
			.build();
	}
}
