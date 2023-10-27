package kr.kernel360.anabada.domain.faq.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateFaqRequest {
	private String title;

	private String content;

}
