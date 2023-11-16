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
public class CreateFaqResponse {
	@ApiModelProperty(value = "FAQ 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "FAQ 제목", example = "제가 등록한 교환이 보이지 않아요.")
	private String title;

	@ApiModelProperty(value = "FAQ 내용", example = "페이지를 새로고침 해주세요.")
	private String content;

	public static CreateFaqResponse of(Faq faq) {
		return CreateFaqResponse.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
	}

}
