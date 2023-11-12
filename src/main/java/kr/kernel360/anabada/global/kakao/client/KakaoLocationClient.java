package kr.kernel360.anabada.global.kakao.client;

import kr.kernel360.anabada.global.config.KakaoFeignConfiguration;
import kr.kernel360.anabada.global.kakao.dto.KakaoPlaceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "kakaoLocationClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoLocationClient {

    @GetMapping
    KakaoPlaceResponse findPlaceByCoordinates(URI baseURL, @RequestHeader("Authorization") String apiKey,
                                              @RequestParam("x") String x, @RequestParam("y") String y,
                                              @RequestParam(value = "input_coord", required = false) String inputCoordinateType,
                                              @RequestParam(value = "output_coord", required = false) String outputCoordinateType);
}
