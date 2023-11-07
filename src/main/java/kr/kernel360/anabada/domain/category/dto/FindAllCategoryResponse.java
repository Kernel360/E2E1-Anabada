package kr.kernel360.anabada.domain.category.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllCategoryResponse {
	List<FindCategoryResponse> categories;

	public static FindAllCategoryResponse of(List<FindCategoryResponse> categories) {
		return FindAllCategoryResponse.builder()
			.categories(categories)
			.build();
	}
}
