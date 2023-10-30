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
public class CreateCategoryResponse {
	private Long id;

	private String name;

	public static CreateCategoryResponse of(Category category) {
		return CreateCategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.build();
	}
}
