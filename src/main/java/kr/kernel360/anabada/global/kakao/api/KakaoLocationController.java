package kr.kernel360.anabada.global.kakao.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.global.kakao.service.KakaoLocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "카카오 MAP API", description = "/api/v1/kakao/location")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class KakaoLocationController {
	private final KakaoLocationService kakaoLocationService;

	@ApiOperation(value = "현재 위치의 위도와 경도로 행정구역 법정동 호출")
	@ApiResponses({@ApiResponse(code = 200, message = "현재 위치의 변환값 호출 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "x", required = true
			, dataType = "double", paramType = "query", defaultValue = "21.12334"),
		@ApiImplicitParam(name = "y", required = true
			, dataType = "double", paramType = "query", defaultValue = "10.12113")
	})
	@GetMapping("/v1/kakao/location")
	public ResponseEntity<PlaceDto> findPlaceByCoordinates(@RequestParam("x") double x, @RequestParam("y") double y) {
		PlaceDto placeDto = kakaoLocationService.findPlaceByCoordinates(x, y);

		return ResponseEntity.ok().body(placeDto);
	}
}
