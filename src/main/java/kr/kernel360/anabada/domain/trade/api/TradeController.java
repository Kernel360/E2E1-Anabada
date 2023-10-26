package kr.kernel360.anabada.domain.trade.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.trade.dto.FindAllTradeResponse;
import kr.kernel360.anabada.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {
	private final TradeService tradeService;

	@GetMapping("/v1/trades")
	public ResponseEntity findAll() {
		FindAllTradeResponse trades = tradeService.findAll();
		return ResponseEntity.ok(trades);
	}
}
