package kr.kernel360.anabada.domain.tradeoffer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferDto;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;

public interface TradeOfferRepository extends JpaRepository<TradeOffer, Long> {

}
