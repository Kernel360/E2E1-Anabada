package kr.kernel360.anabada.domain.faq.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateFaqRequest {
	@ApiModelProperty(value = "FAQ 아이디", example = "1", required = true)
	private Long faqId;

	@ApiModelProperty(value = "FAQ 제목", example = "제가 쓴 글이 보이나요?", required = true)
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "글이 보이지 않는다면, 페이지를 새로고침해주세요"
		, required = true)
	private String content;
}
