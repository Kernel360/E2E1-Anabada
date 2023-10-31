package kr.kernel360.anabada.domain.tradeoffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.tradeoffer.entity.TradeOffer;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;

public interface TradeOfferRepository extends JpaRepository<TradeOffer, Long> {
	Page<TradeOffer> findByMemberAndDeletedStatus(Member member, DeletedStatus deletedStatus, Pageable pageable);

	Page<TradeOffer> findByTradeAndDeletedStatus(Trade trade, DeletedStatus deletedStatus, Pageable pageable);

}
