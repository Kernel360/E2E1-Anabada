package kr.kernel360.anabada.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "카테고리 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "카테고리 이름", example = "전자기기")
	private String name;

	@ApiModelProperty(value = "삭제 상태", dataType = "string", example = "정상")
	private DeletedStatus deletedStatus;

	public static FindCategoryResponse of(Category category) {
		return FindCategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.deletedStatus(category.getDeletedStatus())
			.build();
	}
}
