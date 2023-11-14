package kr.kernel360.anabada.domain.member.repository;

import java.util.List;

import kr.kernel360.anabada.domain.member.dto.AgeGroupDto;

public interface MemberRepositoryCustom {
	List<AgeGroupDto> countMembersByAgeGroup();
}
