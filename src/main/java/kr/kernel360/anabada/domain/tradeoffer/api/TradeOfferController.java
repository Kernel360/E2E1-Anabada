package kr.kernel360.anabada.domain.tradeoffer.api;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.service.TradeOfferService;
import kr.kernel360.anabada.global.FileHandler;
import kr.kernel360.anabada.global.error.code.TradeOfferErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Api(tags = "교환 요청 API", description = "/api/v1/trade-offers")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeOfferController {
	private final TradeOfferService tradeOfferService;
	private final FileHandler fileHandler;
	private final Path rootLocation = Paths.get("../../images/tradeOffer");

	@ApiOperation(value = "조건에 따른 모든 교환 요청 조회 -- 사용할 수 있는 조건 : 교환 요청 상태, 교환 요청 삭제 상태, 회원 아이디, 교환 아이디")
	@ApiResponses({@ApiResponse(code = 200, message = "조건에 따른 모든 교환 요청 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "page_number", required = true
		, dataType = "int", paramType = "query", defaultValue = "1")
	@GetMapping("/v1/trade-offers")
	public ResponseEntity<FindAllTradeOfferResponse> findAll(FindAllTradeOfferRequest findAllTradeOfferRequest,
		    											     @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		Pageable pageable = PageRequest.of(pageNo<1 ? 0 : pageNo-1, 10);
		FindAllTradeOfferResponse findAllTradeOfferResponse = tradeOfferService.findAll(findAllTradeOfferRequest, pageable);

		return ResponseEntity.ok(findAllTradeOfferResponse);
	}

	@ApiOperation(value = "교환 요청 추가")
	@ApiResponses({@ApiResponse(code = 201, message = "교환 요청 추가 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "page_number", required = true
		, dataType = "int", paramType = "query", defaultValue = "1")
	@PostMapping(path = "/v1/trade-offers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Long> create(@ModelAttribute CreateTradeOfferRequest createTradeOfferRequest,
		 							   @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
		if (imageFile != null && !imageFile.isEmpty()) {
			String imagePath = fileHandler.parseFileInfo(imageFile,"tradeOffer");
			createTradeOfferRequest.setImagePath(imagePath);
		}
		Long createdTradeOfferId = tradeOfferService.create(createTradeOfferRequest, createTradeOfferRequest.getTradeId());
		URI url = URI.create("api/v1/trades/trade_offers/" + createdTradeOfferId);

		return ResponseEntity.created(url).body(createdTradeOfferId);
	}

	@ApiOperation(value = "교환 요청 수락")
	@ApiResponses({@ApiResponse(code = 204, message = "교환 요청 수락 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "trade_offer_id", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@PutMapping("/v1/trade-offers/{tradeOfferId}/accept")
	public ResponseEntity<Void> accept(@PathVariable Long tradeOfferId) {
		tradeOfferService.accept(tradeOfferId);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "교환 요청 삭제")
	@ApiResponses({@ApiResponse(code = 204, message = "교환 요청 삭제 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "trade_offer_id", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@DeleteMapping("/v1/trade-offers/{tradeOfferId}")
	public ResponseEntity<Long> remove(@PathVariable Long tradeOfferId) {
		Long removedTradeOfferId = tradeOfferService.remove(tradeOfferId);

		return ResponseEntity.ok(removedTradeOfferId);
	}

	@ApiOperation(value = "단일 교환 요청 조회")
	@ApiResponses({@ApiResponse(code = 204, message = "단일 교환 요청 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 교환 요청 입니다. \t\n 존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "trade_offer_id", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@GetMapping("/v1/trade-offers/{tradeOfferId}")
	public ResponseEntity<FindTradeOfferResponse> find(@PathVariable Long tradeOfferId) {
		FindTradeOfferResponse findTradeOfferResponse = tradeOfferService.find(tradeOfferId);

		return ResponseEntity.ok(findTradeOfferResponse);
	}

	@ApiOperation(value = "교환 요청 이미지 디스플레이")
	@ApiResponses({@ApiResponse(code = 200, message = "교환 요청 이미지 디스플레이 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "파일 경로를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "image_name", required = true
		, dataType = "string", paramType = "path"
		, defaultValue = "src/main/resources/static/images/trade/23110916241318_카카오 로고 Yellow.png")
	@GetMapping("/images/tradeOffer/{imageName}")
	public ResponseEntity<UrlResource> showImage(@PathVariable String imageName) {
		try {
			Path file = rootLocation.resolve(imageName);
			UrlResource resource = new UrlResource(file.toUri());
			return ResponseEntity.ok().body(resource);
		} catch (MalformedURLException e) {
			throw new BusinessException(TradeOfferErrorCode.NOT_FOUND_FILE_PATH);
		}
	}
}
