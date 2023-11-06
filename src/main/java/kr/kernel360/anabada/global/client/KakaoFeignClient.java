package kr.kernel360.anabada.global.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kernel360.anabada.global.dto.KakaoPlaceResponse;

@FeignClient(name = "kakaoClient", url = "https://dapi.kakao.com")
public interface KakaoFeignClient {
	@GetMapping("/v2/local/geo/coord2regioncode.json")
	KakaoPlaceResponse findPlaceByCoordinates(@RequestHeader("Authorization") String apiKey,
		@RequestParam("x") String x, @RequestParam("y") String y,
		@RequestParam(value = "input_coord", required = false) String inputCoordinateType,
		@RequestParam(value = "output_coord", required = false) String outputCoordinateType);

}
