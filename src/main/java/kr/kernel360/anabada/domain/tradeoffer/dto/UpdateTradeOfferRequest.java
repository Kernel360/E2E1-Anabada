package kr.kernel360.anabada.domain.tradeoffer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTradeOfferRequest {
	private Long id;

	private String title;

	private String content;

	private String imagePath;
}
