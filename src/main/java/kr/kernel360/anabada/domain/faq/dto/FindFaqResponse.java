package kr.kernel360.anabada.domain.faq.dto;

import java.time.LocalDateTime;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
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

	private DeletedStatus deletedStatus;

	private String createdBy;

	private String createdDate;

	public static FindFaqResponse of(FindFaqDto faq) {
		return FindFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.createdBy(faq.getCreatedBy())
			.createdDate(faq.getCreatedDate())
			.build();
	}
}
