package kr.kernel360.anabada.domain.category.dto;

import kr.kernel360.anabada.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryResponse {
	private Long id;

	private String name;

	public static UpdateCategoryResponse of(Category category) {
		return UpdateCategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.build();
	}
}
