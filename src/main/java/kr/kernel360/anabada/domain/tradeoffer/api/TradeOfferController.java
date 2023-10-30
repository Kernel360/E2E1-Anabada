package kr.kernel360.anabada.domain.tradeoffer.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.tradeoffer.dto.CreateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.UpdateTradeOfferResponse;
import kr.kernel360.anabada.domain.tradeoffer.service.TradeOfferService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeOfferController {
	private final TradeOfferService tradeOfferService;

	@PostMapping("/v1/trades/{tradeId}/trade_offers/")
	public ResponseEntity<Long> create(CreateTradeOfferRequest createTradeOfferRequest, @PathVariable Long tradeId,
		Long memberId) {
		Long id = tradeOfferService.create(createTradeOfferRequest, tradeId, memberId);
		URI url = URI.create("v1/trades/" + tradeId + "/" + id + "trade_offers");
		return ResponseEntity.created(url).body(id);
	}

	@PutMapping("/v1/trades/{tradeId}/trade_offers/")
	public ResponseEntity<UpdateTradeOfferResponse> update(UpdateTradeOfferRequest updateTradeOfferRequest, @PathVariable Long tradeId) {
		UpdateTradeOfferResponse updateTradeOfferResponse = tradeOfferService.update(updateTradeOfferRequest);
		return ResponseEntity.ok(updateTradeOfferResponse);
	}
}