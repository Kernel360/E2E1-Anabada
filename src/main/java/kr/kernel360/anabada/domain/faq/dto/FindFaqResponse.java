package kr.kernel360.anabada.domain.faq.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindFaqResponse {
	@ApiModelProperty(value = "FAQ 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "FAQ 제목", example = "제가 쓴 글이 보이지 않아요.")
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "페이지를 새로고침해주세요.")
	private String content;

	@ApiModelProperty(value = "FAQ 상태", example = "정상")
	private DeletedStatus deletedStatus;

	@ApiModelProperty(value = "FAQ 작성자", example = "집에가고싶은관리자")
	private String createdBy;

	@ApiModelProperty(value = "FAQ 작성일", example = "2023-11-10")
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
