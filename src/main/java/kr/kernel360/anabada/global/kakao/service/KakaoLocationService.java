package kr.kernel360.anabada.global.kakao.service;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.place.dto.PlaceResponse;
import kr.kernel360.anabada.global.error.code.KakaoErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import kr.kernel360.anabada.global.kakao.client.KakaoLocationClient;
import kr.kernel360.anabada.global.kakao.dto.KakaoPlaceResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoLocationService {
    @Value("${kakao.rest-api-key}")
    private String apiKey;

    @Value("${kakao.place-api-uri}")
    private String kakaoPlaceApiUrl;

    private final KakaoLocationClient kakaoLocationClient;

    public PlaceDto findPlaceByCoordinates(@RequestParam("x") double x, @RequestParam("y") double y) {
        String inputCoordinateType = "";
        String outputCoordinateType = "";

        KakaoPlaceResponse kakaoPlaceResponse = null;
        List<PlaceResponse> places = null;
        PlaceResponse resultResponse = null;
        try {
            kakaoPlaceResponse = kakaoLocationClient
                    .findPlaceByCoordinates(new URI(kakaoPlaceApiUrl), "KakaoAK " + apiKey
                            , String.valueOf(y), String.valueOf(x)
                            , inputCoordinateType, outputCoordinateType);
            places = kakaoPlaceResponse.getDocuments();
            resultResponse = places.stream()
                    .filter(place -> place
                            .getRegionType()
                            .equals("B"))
                    .findFirst()
                    .orElseThrow(() -> new BusinessException(KakaoErrorCode.NOT_FOUND_PLACE));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return PlaceDto.of(resultResponse);
    }
}
