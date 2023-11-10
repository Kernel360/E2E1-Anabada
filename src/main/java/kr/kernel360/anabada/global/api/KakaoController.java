package kr.kernel360.anabada.global.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.global.service.KakaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class KakaoController {

	private final KakaoService kakaoService;

	@GetMapping("/findLocation")
	public ResponseEntity<PlaceDto> findPlaceByCoordinates(@RequestParam("x") double x, @RequestParam("y") double y) {
		PlaceDto placeDto = kakaoService.findPlaceByCoordinates(x, y);

		return ResponseEntity.ok().body(placeDto);
	}
}
