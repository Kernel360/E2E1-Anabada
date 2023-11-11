package kr.kernel360.anabada.domain.trade.repository;

import static kr.kernel360.anabada.domain.category.entity.QCategory.*;
import static kr.kernel360.anabada.domain.member.entity.QMember.*;
import static kr.kernel360.anabada.domain.trade.entity.QTrade.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.QFindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.global.commons.domain.TradeType;

public class TradeRepositoryImpl implements TradeRepositoryCustom{
	// @Query("select new kr.kernel360.anabada.domain.trade.dto.FindTradeDto("
	// 	+ "t.id, t.tradeType, c.name, t.title, m.nickname, t.createdDate) "
	// 	+ "   from Trade t"
	// 	+ "   left outer join t.category c"
	// 	+ "   left outer join t.member m"
	// 	+ "   order by t.id desc")
	// List<FindTradeDto> findTrades();

	private final JPAQueryFactory queryFactory;

	public TradeRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<FindTradeDto> findTrades(TradeSearchCondition tradeSearchCondition, Pageable pageable) {
		List<FindTradeDto> content = queryFactory
			.select(new QFindTradeDto(
				trade.id,
				trade.tradeType,
				trade.tradeStatus,
				trade.deletedStatus,
				category.name,
				trade.title,
				member.nickname,
				trade.createdDate
			))
			.from(trade)
			.leftJoin(trade.category, category)
			.leftJoin(trade.member, member)
			.where(tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryNameEq(tradeSearchCondition.getCategoryName()))
			.orderBy(trade.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(trade.count())
			.from(trade)
			.leftJoin(trade.category, category)
			.leftJoin(trade.member, member)
			.where(tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryNameEq(tradeSearchCondition.getCategoryName()))
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression tradeTypeEq(String tradeType) {
		return hasText(tradeType) ? trade.tradeType.eq(TradeType.valueOf(tradeType)) : null;
	}

	private BooleanExpression categoryNameEq(String categoryName) {
		return hasText(categoryName) ? category.name.eq(categoryName) : null;
	}


}
