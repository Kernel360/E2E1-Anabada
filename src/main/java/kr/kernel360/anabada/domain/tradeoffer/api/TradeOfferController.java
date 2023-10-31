package kr.kernel360.anabada.domain.tradeoffer.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.service.TradeOfferService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeOfferController {
	private final TradeOfferService tradeOfferService;

	@PostMapping("/v1/trade_offers/")
	public ResponseEntity<Long> create(@RequestBody CreateTradeOfferRequest createTradeOfferRequest) {
		Long id = tradeOfferService.create(createTradeOfferRequest, createTradeOfferRequest.getTradeId(), createTradeOfferRequest.getMemberId());
		URI url = URI.create("api/v1/trades/trade_offers" + id);
		return ResponseEntity.created(url).body(id);
	}

	@PutMapping("/v1/trade_offers/")
	public ResponseEntity<UpdateTradeOfferResponse> update(@RequestBody UpdateTradeOfferRequest updateTradeOfferRequest) {
		UpdateTradeOfferResponse updateTradeOfferResponse = tradeOfferService.update(updateTradeOfferRequest);
		return ResponseEntity.ok(updateTradeOfferResponse);
	}

	@DeleteMapping("v1/trade_offers/{tradeOfferId}")
	public ResponseEntity<Long> remove(@PathVariable Long tradeOfferId) {
		tradeOfferService.remove(tradeOfferId);
		return ResponseEntity.ok(tradeOfferId);
	}

	@GetMapping("v1/trade_offers/{tradeOfferId}")
	public ResponseEntity<FindTradeOfferResponse> find(@PathVariable Long tradeOfferId) {
		FindTradeOfferResponse findTradeOfferResponse = tradeOfferService.find(tradeOfferId);
		return ResponseEntity.ok(findTradeOfferResponse);
	}
}
