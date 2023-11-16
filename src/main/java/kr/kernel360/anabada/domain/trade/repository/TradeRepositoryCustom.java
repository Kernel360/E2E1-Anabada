package kr.kernel360.anabada.domain.trade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.kernel360.anabada.domain.place.entity.Place;
import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;

public interface TradeRepositoryCustom {
	Page<FindTradeDto> findTrades(TradeSearchCondition tradeSearchCondition, Place placeCondition, Pageable pageable);
	FindTradeDto findTrade(Long tradeId);
	Long countTradeOfferByTradeIdAndEmail(Long tradeId, String email);

}
