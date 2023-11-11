package kr.kernel360.anabada.domain.trade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>, TradeRepositoryCustom {
	// @Query("select new kr.kernel360.anabada.domain.trade.dto.FindTradeDto("
	// 	+ "t.id, t.tradeType, c.name, t.title, m.nickname, t.createdDate) "
	// 	+ "   from Trade t"
	// 	+ "   left outer join t.category c"
	// 	+ "   left outer join t.member m"
	// 	+ "   order by t.id desc")
	// List<FindTradeDto> findTrades();

	@Query("select new kr.kernel360.anabada.domain.trade.dto.FindTradeDto("
		+ "t.id, t.tradeType, t.tradeStatus, t.deletedStatus, c.name, t.title, m.nickname, t.createdDate, t.content, t.imagePath) "
		+ "   from Trade t"
		+ "   left outer join t.category c"
		+ "   left outer join t.member m"
		+ "  where t.id = :tradeId")
	Optional<FindTradeDto> findTrade(@Param("tradeId") Long tradeId);
}
