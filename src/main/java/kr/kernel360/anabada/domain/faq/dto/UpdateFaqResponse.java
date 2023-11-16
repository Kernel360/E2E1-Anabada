package kr.kernel360.anabada.domain.faq.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "FAQ 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "FAQ 제목", example = "제가 쓴 글이 보이나요?")
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "글이 보이지 않는다면, 페이지를 새로고침해주세요")
	private String content;

	public static UpdateFaqResponse of(Faq faq) {
		return UpdateFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
	}
}
