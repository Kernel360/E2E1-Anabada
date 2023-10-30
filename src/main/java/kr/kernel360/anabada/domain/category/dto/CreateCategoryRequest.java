package kr.kernel360.anabada.domain.category.dto;

import javax.validation.constraints.NotBlank;

import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryRequest {
	@NotBlank
	private String name;

	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	public static Category toEntity(CreateCategoryRequest createCategoryRequest) {
		return Category.builder()
			.name(createCategoryRequest.name)
			.deletedStatus(createCategoryRequest.deletedStatus)
			.build();
	}
}
