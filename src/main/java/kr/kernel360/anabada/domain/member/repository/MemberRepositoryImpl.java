package kr.kernel360.anabada.domain.member.repository;

import static kr.kernel360.anabada.domain.member.entity.QMember.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.kernel360.anabada.domain.member.dto.AgeGroupDto;
import kr.kernel360.anabada.domain.member.dto.GenderDto;
import kr.kernel360.anabada.domain.member.dto.QAgeGroupDto;
import kr.kernel360.anabada.domain.member.dto.QGenderDto;
import kr.kernel360.anabada.global.utils.OrderByNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<AgeGroupDto> countMembersByAgeGroup() {
		return queryFactory.select(new QAgeGroupDto(
			member.ageGroup,
			member.id.count().as("count")
		))
			.from(member)
			.groupBy(member.ageGroup)
			.orderBy(OrderByNull.DEFAULT)
			.fetch();
	}

	@Override
	public List<GenderDto> countMembersByGender() {
		return queryFactory.select(new QGenderDto(
			member.gender,
			member.id.count().as("count")
		))
			.from(member)
			.groupBy(member.gender)
			.orderBy(OrderByNull.DEFAULT)
			.fetch();
	}
}
