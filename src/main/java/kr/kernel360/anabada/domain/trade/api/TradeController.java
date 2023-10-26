package kr.kernel360.anabada.domain.trade.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
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
}
