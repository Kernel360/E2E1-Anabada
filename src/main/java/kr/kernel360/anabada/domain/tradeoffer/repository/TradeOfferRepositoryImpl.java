package kr.kernel360.anabada.domain.tradeoffer.repository;

import static kr.kernel360.anabada.domain.member.entity.QMember.*;
import static kr.kernel360.anabada.domain.tradeoffer.entity.QTradeOffer.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindAllTradeOfferRequest;
import kr.kernel360.anabada.domain.tradeoffer.dto.FindTradeOfferDto;
import kr.kernel360.anabada.domain.tradeoffer.dto.QFindTradeOfferDto;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeOfferRepositoryImpl implements TradeOfferRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<FindTradeOfferDto> findAll(FindAllTradeOfferRequest findAllTradeOfferRequest, Pageable pageable) {
		List<FindTradeOfferDto> content = queryFactory
			.select(new QFindTradeOfferDto(
				tradeOffer.id,
				tradeOffer.title,
				tradeOffer.tradeOfferStatus,
				tradeOffer.deletedStatus,
				member.nickname,
				tradeOffer.trade.id,
				tradeOffer.createdDate
			))
			.from(tradeOffer)
			.leftJoin(tradeOffer.member, member)
			.where(
				memberIdEq(findAllTradeOfferRequest.getMemberId()),
				tradeIdEq(findAllTradeOfferRequest.getTradeId()),
				tradeOffer.deletedStatus.eq(DeletedStatus.FALSE)
				)
			.orderBy(tradeOffer.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(tradeOffer.count())
			.from(tradeOffer)
			.leftJoin(tradeOffer.member, member)
			.where(
				memberIdEq(findAllTradeOfferRequest.getMemberId()),
				tradeIdEq(findAllTradeOfferRequest.getTradeId()),
				tradeOffer.deletedStatus.eq(DeletedStatus.FALSE)
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, total);

	}
	private BooleanExpression memberIdEq(Long memberId) {
		return memberId != null ? member.id.eq(memberId) : null;
	}
	private BooleanExpression tradeIdEq(Long tradeId) {
		return tradeId != null ? tradeOffer.trade.id.eq(tradeId) : null;
	}

	@Override
	public FindTradeOfferDto find(Long tradeOfferId) {
		return queryFactory
			.select(new QFindTradeOfferDto(
				tradeOffer.id,
				tradeOffer.title,
				tradeOffer.content,
				tradeOffer.imagePath,
				tradeOffer.tradeOfferStatus,
				tradeOffer.deletedStatus,
				member.nickname,
				tradeOffer.createdDate,
				tradeOffer.trade.id
			))
			.from(tradeOffer)
			.leftJoin(tradeOffer.member, member)
			.where(tradeOffer.id.eq(tradeOfferId))
			.fetchOne();
	}

	@Override
	public void updateTradeOffersByTradeOfferIdNeAndTradeEq(Long tradeOfferId, Trade findTrade) {

		queryFactory
			.update(tradeOffer)
			.set(tradeOffer.tradeOfferStatus, TradeOfferStatus.REQUEST_EXPIRED)
			.where(
				tradeOffer.trade.eq(findTrade).and(tradeOffer.id.ne(tradeOfferId)),
				tradeOffer.deletedStatus.eq(DeletedStatus.FALSE)
			)
			.execute();
	}
}
