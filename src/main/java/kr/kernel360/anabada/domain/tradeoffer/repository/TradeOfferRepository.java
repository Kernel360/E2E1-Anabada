package kr.kernel360.anabada.domain.tradeoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;

public interface TradeOfferRepository extends JpaRepository<TradeOffer, Long>, TradeOfferRepositoryCustom {
}
