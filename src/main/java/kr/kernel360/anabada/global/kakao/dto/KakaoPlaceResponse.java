package kr.kernel360.anabada.global.kakao.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import kr.kernel360.anabada.domain.place.dto.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoPlaceResponse {
	private Meta meta;

	private List<PlaceResponse> documents;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Meta {
		private Long totalCount;
	}
}
