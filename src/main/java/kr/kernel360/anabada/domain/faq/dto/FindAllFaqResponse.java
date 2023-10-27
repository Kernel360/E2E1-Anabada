package kr.kernel360.anabada.domain.faq.dto;

import java.util.List;

import kr.kernel360.anabada.domain.faq.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllFaqResponse {
	private List<FindAllFaqDto> faqs;

	public static FindAllFaqResponse of(List<Faq> faqs) {
		return FindAllFaqResponse.builder()
			.faqs(faqs.stream().map(FindAllFaqDto::of).toList())
			.build();
	}

}
