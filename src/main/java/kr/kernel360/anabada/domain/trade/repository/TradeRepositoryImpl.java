package kr.kernel360.anabada.domain.trade.repository;

import static kr.kernel360.anabada.domain.category.entity.QCategory.*;
import static kr.kernel360.anabada.domain.member.entity.QMember.*;
import static kr.kernel360.anabada.domain.trade.entity.QTrade.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.trade.dto.FindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.QFindTradeDto;
import kr.kernel360.anabada.domain.trade.dto.TradeSearchCondition;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeRepositoryImpl implements TradeRepositoryCustom {

	private final JPAQueryFactory queryFactory;

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
			.where(
				tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryIdEq(tradeSearchCondition.getCategoryId()),
				tradeCreatedByEq(tradeSearchCondition.getCreatedBy()),
				tradeTitleLike(tradeSearchCondition.getTitle())
			)
			.orderBy(trade.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(trade.count())
			.from(trade)
			.leftJoin(trade.member, member)
			.where(
				tradeTypeEq(tradeSearchCondition.getTradeType()),
				categoryIdEq(tradeSearchCondition.getCategoryId())
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public FindTradeDto findTrade(Long tradeId) {
		return queryFactory
			.select(new QFindTradeDto(
				trade.id,
				trade.tradeType,
				trade.tradeStatus,
				trade.deletedStatus,
				category.name,
				trade.title,
				member.nickname,
				trade.createdDate,
				trade.content,
				trade.imagePath
			))
			.from(trade)
			.leftJoin(trade.category, category)
			.leftJoin(trade.member, member)
			.where(trade.id.eq(tradeId))
			.fetchOne();
	}

	private BooleanExpression tradeTypeEq(String tradeType) {
		return hasText(tradeType) ? trade.tradeType.eq(TradeType.valueOf(tradeType)) : null;
	}

	private BooleanExpression categoryIdEq(Long categoryId) {
		return categoryId != null ? trade.category.id.eq(categoryId) : null;
	}

	private BooleanExpression tradeTitleLike(String title) {
		return hasText(title) ? trade.title.contains(title) : null;
	}

	private BooleanExpression tradeCreatedByEq(String createdBy) {
		return hasText(createdBy) ? member.nickname.eq(createdBy) : null;
	}
}
