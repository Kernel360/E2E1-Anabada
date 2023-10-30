package kr.kernel360.anabada.domain.faq.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindFaqDto {
	private Long id;

	private String title;

	private String content;

	private String createdBy;

	private LocalDateTime createdDate;

	public FindFaqDto(Long id, String title, String createdBy, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public FindFaqDto(Long id, String title, String content, String createdBy, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
}
