package kr.kernel360.anabada.domain.faq.dto;

import kr.kernel360.anabada.domain.faq.entity.Faq;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindAllFaqDto {
	private Long id;

	private String title;

	public static FindAllFaqDto of(Faq faq) {
		return FindAllFaqDto.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.build();
	}
}
