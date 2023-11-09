package kr.kernel360.anabada.global.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kernel360.anabada.global.config.KakaoFeignConfiguration;
import kr.kernel360.anabada.global.dto.KakaoInfoResponse;
import kr.kernel360.anabada.global.dto.KakaoPlaceResponse;
import kr.kernel360.anabada.global.dto.KakaoTokenResponse;

@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoClient {

	@GetMapping
	KakaoPlaceResponse findPlaceByCoordinates(URI baseURL, @RequestHeader("Authorization") String apiKey,
		@RequestParam("x") String x, @RequestParam("y") String y,
		@RequestParam(value = "input_coord", required = false) String inputCoordinateType,
		@RequestParam(value = "output_coord", required = false) String outputCoordinateType);

	@GetMapping
	void getCode(URI baseUrl, @RequestParam("response_type") String code,
		@RequestParam("client_id") String apiKey,
		@RequestParam("redirect_uri") String redirectUri);


	@PostMapping
	KakaoInfoResponse getInfo(URI baseURL, @RequestHeader("Authorization") String accessToken);

	@PostMapping
	KakaoTokenResponse getToken(URI baseURL, @RequestParam("client_id") String apiKey,
		@RequestParam("redirect_uri") String redirectUrl,
		@RequestParam("code") String code,
		@RequestParam("grant_type") String grantType);
}
