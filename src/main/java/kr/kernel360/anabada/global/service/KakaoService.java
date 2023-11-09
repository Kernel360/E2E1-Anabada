package kr.kernel360.anabada.global.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.place.dto.PlaceResponse;
import kr.kernel360.anabada.global.client.KakaoClient;
import kr.kernel360.anabada.global.dto.KakaoInfoResponse;
import kr.kernel360.anabada.global.dto.KakaoPlaceResponse;
import kr.kernel360.anabada.global.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoService {
	@Value("${kakao.rest-api-key}")
	private String apiKey;

	@Value("${kakao.authorize-url}")
	private String kakaoAuthorizeUrl;

	@Value("${kakao.auth-url}")
	private String kakaoAuthUrl;

	@Value("${kakao.place-api-url}")
	private String kakaoPlaceApiUrl;

	@Value("${kakao.user-api-url}")
	private String kakaoUserApiUrl;

	@Value("${kakao.redirect-url}")
	private String redirectUrl;

	private final KakaoClient kakaoClient;

	public void getCode() throws URISyntaxException {
		kakaoClient.getCode(new URI(kakaoAuthorizeUrl), apiKey, redirectUrl, "code");
	}

	public KakaoInfoResponse getInfo(final String code) {
		final KakaoTokenResponse tokenResponse = getToken(code);
		try {
			return kakaoClient.getInfo(new URI(kakaoUserApiUrl), tokenResponse.getTokenType() +
				" " + tokenResponse.getAccessToken());
		} catch (RuntimeException | URISyntaxException e) {
			e.printStackTrace();
			return KakaoInfoResponse.fail();
		}
	}

	public KakaoTokenResponse getToken(final String code) {
		try {
			return kakaoClient.getToken(new URI(kakaoAuthUrl), apiKey, redirectUrl
				, code, "authorization_code");
		} catch (RuntimeException | URISyntaxException e) {
			e.printStackTrace();
			return KakaoTokenResponse.fail();
		}
	}

	public PlaceDto findPlaceByCoordinates(@RequestParam("x") double x, @RequestParam("y") double y) {
		String inputCoordinateType = "";
		String outputCoordinateType = "";

		KakaoPlaceResponse kakaoPlaceResponse = null;
		List<PlaceResponse> places = null;
		PlaceResponse resultResponse = null;
		try {
			kakaoPlaceResponse = kakaoClient
				.findPlaceByCoordinates(new URI(kakaoPlaceApiUrl), "KakaoAK " + apiKey
					, String.valueOf(y), String.valueOf(x)
					, inputCoordinateType, outputCoordinateType);
			places = kakaoPlaceResponse.getDocuments();
			resultResponse = places.stream()
				.filter(place -> place
					.getRegionType()
					.equals("B"))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("찾는 지역 정보가 존재하지 않습니다."));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return PlaceDto.of(resultResponse);
	}
}
