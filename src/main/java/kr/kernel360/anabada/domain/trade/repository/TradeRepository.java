package kr.kernel360.anabada.domain.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kernel360.anabada.domain.trade.dto.FindAllTradeDto;
import kr.kernel360.anabada.domain.trade.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
	@Query("select new kr.kernel360.anabada.domain.trade.dto.FindAllTradeDto("
		+ "t.id, t.tradeType, c.name, t.title, m.nickname, t.createDate) "
		+ "   from Trade t"
		+ "   left outer join t.category c"
		+ "   left outer join t.member m")
	List<FindAllTradeDto> findTrades();
}
