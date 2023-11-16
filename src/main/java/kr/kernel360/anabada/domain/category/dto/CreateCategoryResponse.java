package kr.kernel360.anabada.domain.category.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "카테고리 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "카테고리 이름", example = "전자기기")
	private String name;

	public static CreateCategoryResponse of(Category category) {
		return CreateCategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.build();
	}
}
