package kr.kernel360.anabada.global.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.place.dto.PlaceResponse;
import kr.kernel360.anabada.domain.place.dto.PlaceSearch;
import kr.kernel360.anabada.global.client.KakaoFeignClient;
import kr.kernel360.anabada.global.dto.KakaoPlaceResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class KakaoFeignController {
	@Value("${kakao-api-key}")
	private String apiKey;

	private final KakaoFeignClient kakaoFeignClient;

	@GetMapping("/places/result")
	public ResponseEntity<PlaceDto> findPlaceByCoordinates(@RequestBody PlaceSearch placeSearch) {
		String inputCoordinateType = "";
		String outputCoordinateType = "";

		KakaoPlaceResponse kakaoPlaceResponse = kakaoFeignClient
			.findPlaceByCoordinates("KakaoAK " + apiKey
				, placeSearch.getX(), placeSearch.getY()
				, inputCoordinateType, outputCoordinateType);

		List<PlaceResponse> places = kakaoPlaceResponse.getDocuments();

		PlaceResponse resultResponse = places.stream()
			.filter(place -> place
				.getRegionType()
				.equals("B"))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("찾는 지역 정보가 존재하지 않습니다."));

		return ResponseEntity.ok().body(PlaceDto.of(resultResponse));
	}
}
