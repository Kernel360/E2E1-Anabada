package kr.kernel360.anabada.domain.category.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "카테고리 이름", example = "전자기기", required = true)
	private String name;

	@ApiModelProperty(value = "삭제 상태", example = "정상")
	private DeletedStatus deletedStatus = DeletedStatus.FALSE;

	public static Category toEntity(CreateCategoryRequest createCategoryRequest) {
		return Category.builder()
			.name(createCategoryRequest.name)
			.deletedStatus(createCategoryRequest.deletedStatus)
			.build();
	}
}
