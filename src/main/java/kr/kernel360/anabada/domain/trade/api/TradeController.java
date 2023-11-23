package kr.kernel360.anabada.domain.trade.api;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.trade.dto.CreateTradeRequest;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradesByStateResponse;
import kr.kernel360.anabada.domain.trade.dto.FindTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.domain.trade.service.TradeService;
import kr.kernel360.anabada.global.FileHandler;
import kr.kernel360.anabada.global.error.code.TradeErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Api(tags = "교환 API", description = "/api/v1/trades")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {
	private final TradeService tradeService;
	private final FileHandler fileHandler;
	private final ObjectMapper objectMapper;
	private final Path rootLocation = Paths.get(Paths.get("images/trade").toAbsolutePath().toString().replace("/build/libs",""));

	@ApiOperation(value = "조건에 따른 모든 교환 조회 -- 사용할 수 있는 조건 : 제목, 작성자, 교환 타입, 카테고리 아이디")
	@ApiResponses({@ApiResponse(code = 200, message = "조건에 따른 모든 교환 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "page_number", required = true
		, dataType = "int", paramType = "query", defaultValue = "1")
	@GetMapping("/v1/trades")
	public ResponseEntity<FindAllTradeResponse> findAll(TradeSearchCondition tradeSearchCondition,
														@RequestParam(value = "placeDto", required = false) String placeDtoJson,
														@RequestParam(value="pageNo", defaultValue="1") int pageNo
		) {
		Pageable pageable = PageRequest.of(pageNo<1 ? 0 : pageNo-1, 10);
		PlaceDto placeDto = parseJsonToPlaceDto(placeDtoJson);
		FindAllTradeResponse findAllTradeResponse = tradeService.findAll(tradeSearchCondition, placeDto, pageable);

		return ResponseEntity.ok(findAllTradeResponse);
	}

	private PlaceDto parseJsonToPlaceDto(String placeDtoJson) {
		if (placeDtoJson == null) return null;
		try {
			PlaceDto placeDto = objectMapper.readValue(placeDtoJson, PlaceDto.class);
			return placeDto;
		} catch (JsonProcessingException e) {
			throw new BusinessException(TradeErrorCode.NOT_FOUND_PLACE);
		}
	}

	@ApiOperation(value = "단일 교환 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "단일 교환 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다. \t\n 교환 정보를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "trade_id", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@GetMapping("/v1/trades/{tradeId}")
	public ResponseEntity<FindTradeResponse> find(@PathVariable Long tradeId) {
		FindTradeResponse findTradeResponse = tradeService.find(tradeId);

		return ResponseEntity.ok(findTradeResponse);
	}

	@ApiOperation(value = "교환 추가")
	@ApiResponses({@ApiResponse(code = 201, message = "교환 추가 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "image_file", required = true
		, dataType = "MultipartFile", paramType = "query", defaultValue = "1")
	@PostMapping(path = "/v1/trades", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Long> create(
		@ModelAttribute CreateTradeRequest createTradeRequest,
		@RequestParam("placeDto") String placeDtoJson,
		@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
		if (imageFile != null && !imageFile.isEmpty()) {
			String imagePath = fileHandler.parseFileInfo(imageFile,"trade");
			createTradeRequest.setImagePath(imagePath);
		}
		try {
			PlaceDto placeDto = objectMapper.readValue(placeDtoJson, PlaceDto.class);
			Long savedTradeId = tradeService.create(createTradeRequest, placeDto);
			URI uri = URI.create("/api/v1/trades/"+savedTradeId);

			return ResponseEntity.created(uri).build();
		} catch (JsonProcessingException e) {
			throw new BusinessException(TradeErrorCode.NOT_FOUND_PLACE);
		}

	}

	@ApiOperation(value = "교환 이미지 디스플레이")
	@ApiResponses({@ApiResponse(code = 200, message = "교환 이미지 디스플레이 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "파일 경로를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "image_name", required = true
		, dataType = "string", paramType = "path"
		, defaultValue = "src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png")
	@GetMapping("/images/trade/{imageName}")
	public ResponseEntity<UrlResource> showImage(@PathVariable String imageName) {
		try {
			Path file = rootLocation.resolve(imageName);
			UrlResource resource = new UrlResource(file.toUri());
			return ResponseEntity.ok().body(resource);
		} catch (MalformedURLException e) {
			throw new BusinessException(TradeErrorCode.NOT_FOUND_FILE_PATH);
		}
	}

	@ApiOperation(value = "시도별 교환 수 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "시도별 회원 수 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@Secured("ROLE_ADMIN")
	@GetMapping("/v1/trades/state")
	public ResponseEntity<FindAllTradesByStateResponse> countTradeByState() {
		FindAllTradesByStateResponse findAllTradesByStateResponse = tradeService.countTradeByStatus();

		return ResponseEntity.ok(findAllTradesByStateResponse);
	}
}


