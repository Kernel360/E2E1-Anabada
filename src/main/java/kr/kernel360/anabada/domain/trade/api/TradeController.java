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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.trade.dto.CreateTradeRequest;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.FindTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.domain.trade.service.TradeService;
import kr.kernel360.anabada.global.FileHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {
	private final TradeService tradeService;
	private final FileHandler fileHandler;
	private final Path rootLocation = Paths.get("src/main/resources/static/images/trade");

	@GetMapping("/v1/trades")
	public ResponseEntity<FindAllTradeResponse> findAll(TradeSearchCondition tradeSearchCondition,
														@RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		Pageable pageable = PageRequest.of(pageNo<1 ? 0 : pageNo-1, 10);
		FindAllTradeResponse trades = tradeService.findAll(tradeSearchCondition, pageable);
		return ResponseEntity.ok(trades);
	}

	@GetMapping("/v1/trades/{tradeId}")
	public ResponseEntity<FindTradeResponse> find(@PathVariable Long tradeId) {
		FindTradeResponse trade = tradeService.find(tradeId);
		return ResponseEntity.ok(trade);
	}

	@PostMapping(path = "/v1/trades", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Long> create(
		@ModelAttribute CreateTradeRequest createTradeRequest,
		@ModelAttribute PlaceDto placeDto,
		@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

		if (imageFile != null && !imageFile.isEmpty()) {
			String imagePath = fileHandler.parseFileInfo(imageFile,"trade");
			createTradeRequest.setImagePath(imagePath);
		}

		Long savedTradeId = tradeService.create(createTradeRequest, placeDto);
		URI uri = URI.create("/api/v1/trades/"+savedTradeId);
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/images/trade/{imageName}")
	public ResponseEntity<UrlResource> showImage(@PathVariable String imageName) {
		try {
			Path file = rootLocation.resolve(imageName);
			UrlResource resource = new UrlResource(file.toUri());
			return ResponseEntity.ok().body(resource);

		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("잘못된 형식의 URL 입니다");
		}
	}
}


