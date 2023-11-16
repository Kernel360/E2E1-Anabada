package kr.kernel360.anabada.domain.category.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllCategoryResponse {
	@ApiModelProperty(value = "카테고리 목록"
		, example = "[\"전자기기\", \"의류\", \"생활용품\", \"식품\"]")
	List<FindCategoryResponse> categories;

	public static FindAllCategoryResponse of(List<FindCategoryResponse> categories) {
		return FindAllCategoryResponse.builder()
			.categories(categories)
			.build();
	}
}
