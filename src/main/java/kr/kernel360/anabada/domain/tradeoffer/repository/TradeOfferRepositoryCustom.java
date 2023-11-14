package kr.kernel360.anabada.domain.tradeoffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferDto;

public interface TradeOfferRepositoryCustom {
	Page<FindTradeOfferDto> findAll(FindAllTradeOfferRequest findAllTradeOfferRequest, Pageable pageable);

	FindTradeOfferDto find(Long tradeOfferId);
}
