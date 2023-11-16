package kr.kernel360.anabada.domain.faq.dto;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindAllFaqResponse {
	@JsonProperty("faqs")
	private Page<FindFaqDto> faqs;

	public static FindAllFaqResponse of(Page<FindFaqDto> faqs) {
		return FindAllFaqResponse.builder()
			.faqs(faqs)
			.build();
	}
}
