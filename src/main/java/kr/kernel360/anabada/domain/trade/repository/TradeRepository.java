package kr.kernel360.anabada.domain.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.trade.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
