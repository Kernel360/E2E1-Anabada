package kr.kernel360.anabada.domain.faq.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindFaqDto {
	private Long id;

	private String title;

	private String content;

	private DeletedStatus deletedStatus;

	private String createdBy;

	private String createdDate;

	@QueryProjection
	public FindFaqDto(Long id, String title, String createdBy, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.createdBy = createdBy;
		this.createdDate = DateParser.dateToString(createdDate);
	}

	@QueryProjection
	public FindFaqDto(Long id, String title, String content, String createdBy, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
		this.createdDate = DateParser.dateTimeToString(createdDate);
	}
}
