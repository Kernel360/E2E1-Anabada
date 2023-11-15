package kr.kernel360.anabada.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgeGroupDto {
	private String ageGroup;

	private Long count;

	@QueryProjection
	public AgeGroupDto(String ageGroup, Long count) {
		this.ageGroup = ageGroup;
		this.count = count;
	}
}
