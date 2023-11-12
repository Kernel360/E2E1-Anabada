package kr.kernel360.anabada.global.kakao.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kernel360.anabada.global.config.KakaoFeignConfiguration;
import kr.kernel360.anabada.global.kakao.dto.KakaoInfoResponse;
import kr.kernel360.anabada.global.kakao.dto.KakaoTokenResponse;

@FeignClient(name = "kakaoLoginClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoLoginClient {

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
