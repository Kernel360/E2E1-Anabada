package kr.kernel360.anabada.global.kakao.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.kernel360.anabada.global.kakao.client.KakaoLoginClient;
import kr.kernel360.anabada.global.kakao.dto.KakaoInfoResponse;
import kr.kernel360.anabada.global.kakao.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
	@Value("${kakao.rest-api-key}")
	private String apiKey;

	@Value("${kakao.authorize-url}")
	private String kakaoAuthorizeUrl;

	@Value("${kakao.auth-url}")
	private String kakaoAuthUrl;

	@Value("${kakao.user-api-url}")
	private String kakaoUserApiUrl;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	private final KakaoLoginClient kakaoLoginClient;

	public void getCode() throws URISyntaxException {
		kakaoLoginClient.getCode(new URI(kakaoAuthorizeUrl),"code", apiKey , redirectUri);
	}

	public KakaoInfoResponse getInfo(final String code) {
		final KakaoTokenResponse tokenResponse = getToken(code);
		try {
			return kakaoLoginClient.getInfo(new URI(kakaoUserApiUrl), tokenResponse.getTokenType() +
				" " + tokenResponse.getAccessToken());
		} catch (RuntimeException | URISyntaxException e) {
			e.printStackTrace();
			return KakaoInfoResponse.fail();
		}
	}

	public KakaoTokenResponse getToken(final String code) {
		try {
			return kakaoLoginClient.getToken(new URI(kakaoAuthUrl), apiKey, redirectUri
				, code, "authorization_code");
		} catch (RuntimeException | URISyntaxException e) {
			e.printStackTrace();
			return KakaoTokenResponse.fail();
		}
	}
}
