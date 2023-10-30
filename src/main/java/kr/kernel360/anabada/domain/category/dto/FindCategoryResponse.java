package kr.kernel360.anabada.domain.category.dto;

import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCategoryResponse {
	private Long id;

	private String name;

	private DeletedStatus deletedStatus;

	public static FindCategoryResponse of(Category category) {
		return FindCategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.deletedStatus(category.getDeletedStatus())
			.build();
	}
}
