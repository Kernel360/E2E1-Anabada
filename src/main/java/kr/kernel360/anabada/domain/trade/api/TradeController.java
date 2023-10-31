package kr.kernel360.anabada.domain.trade.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.place.dto.PlaceDto;
import kr.kernel360.anabada.domain.trade.dto.CreateTradeRequest;
import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.FindTradeResponse;
import kr.kernel360.anabada.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {
	private final TradeService tradeService;

	@GetMapping("/v1/trades")
	public ResponseEntity<FindAllTradeResponse> findAll() {
		FindAllTradeResponse trades = tradeService.findAll();
		return ResponseEntity.ok(trades);
	}

	@GetMapping("/v1/trades/{tradeId}")
	public ResponseEntity<FindTradeResponse> find(@PathVariable Long tradeId) {
		FindTradeResponse trade = tradeService.find(tradeId);
		return ResponseEntity.ok(trade);
	}

	@PostMapping("/v1/trades")
	public ResponseEntity<Long> create(@RequestBody CreateTradeRequest createTradeRequest, PlaceDto placeDto) {
		Long savedTradeId = tradeService.create(placeDto, createTradeRequest);
		URI uri = URI.create("/api/v1/trades/"+savedTradeId);
		return ResponseEntity.created(uri).build();
	}
}
