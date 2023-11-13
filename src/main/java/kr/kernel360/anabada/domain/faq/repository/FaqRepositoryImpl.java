package kr.kernel360.anabada.domain.faq.repository;

import static kr.kernel360.anabada.domain.faq.entity.QFaq.*;
import static kr.kernel360.anabada.domain.member.entity.QMember.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.faq.dto.FaqSearchCondition;
import kr.kernel360.anabada.domain.faq.dto.FindFaqDto;
import kr.kernel360.anabada.domain.faq.dto.QFindFaqDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FaqRepositoryImpl implements FaqRepositoryCustom {

	public final JPAQueryFactory queryFactory;

	@Override
	public Page<FindFaqDto> findFaqs(FaqSearchCondition faqSearchCondition, Pageable pageable) {
		List<FindFaqDto> content = queryFactory
			.select(new QFindFaqDto(
				faq.id,
				faq.title,
				member.nickname,
				faq.createdDate
			))
			.from(faq)
			.leftJoin(faq.member, member)
			.where(
				faqCreatedByEq(faqSearchCondition.getCreatedBy()),
				faqTitleContain(faqSearchCondition.getTitle())
			)
			.orderBy(faq.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(faq.count())
			.from(faq)
			.where(
				faqCreatedByEq(faqSearchCondition.getCreatedBy()),
				faqTitleContain(faqSearchCondition.getTitle())
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public FindFaqDto findFaq(Long faqId) {
		return queryFactory
			.select(new QFindFaqDto(
				faq.id,
				faq.title,
				faq.content,
				member.nickname,
				faq.createdDate
			))
			.from(faq)
			.leftJoin(faq.member, member)
			.where(
				faq.id.eq(faqId)
			)
			.fetchOne();
	}

	private BooleanExpression faqTitleContain(String title) {
		return hasText(title) ? faq.title.contains(title) : null;
	}

	private BooleanExpression faqCreatedByEq(String createdBy) {
		return hasText(createdBy) ? member.nickname.eq(createdBy) : null;
	}
}
