package kr.kernel360.anabada.global.kakao.api;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.global.kakao.service.KakaoLocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class KakaoLocationController {

	private final KakaoLocationService kakaoLocationService;

	@GetMapping("/findLocation")
	public ResponseEntity<PlaceDto> findPlaceByCoordinates(@RequestParam("x") double x, @RequestParam("y") double y) {
		PlaceDto placeDto = kakaoLocationService.findPlaceByCoordinates(x, y);

		return ResponseEntity.ok().body(placeDto);
	}
}
