package kr.kernel360.anabada.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GenderDto {
	private String gender;

	private Long count;

	@QueryProjection
	public GenderDto(String gender, Long count) {
		this.gender = gender;
		this.count = count;
	}
}
