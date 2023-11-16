package kr.kernel360.anabada.domain.faq.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.utils.DateParser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindFaqDto {
	@ApiModelProperty(value = "FAQ 아이디", example = "1", required = true)
	private Long id;

	@ApiModelProperty(value = "FAQ 제목", example = "제가 쓴 글이 보이지 않아요.", required = true)
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "페이지를 새로고침해주세요.", required = true)
	private String content;

	@JsonProperty(value = "deleted_status")
	@ApiModelProperty(value = "FAQ 상태", example = "정상", required = true)
	private DeletedStatus deletedStatus;

	@JsonProperty(value = "created_by")
	@ApiModelProperty(value = "FAQ 작성자", example = "집에가고싶은관리자", required = true)
	private String createdBy;

	@JsonProperty(value = "created_date")
	@ApiModelProperty(value = "FAQ 작성일", example = "2023-11-10", required = true)
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
