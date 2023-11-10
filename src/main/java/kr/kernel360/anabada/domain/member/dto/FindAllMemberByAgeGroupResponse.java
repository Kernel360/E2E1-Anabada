package kr.kernel360.anabada.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindAllMemberByAgeGroupResponse {
	private Long teenagers;

	private Long twenties;

	private Long thirties;

	private Long forties;

	private Long fifties;

	private Long sixties;

	// private List<AgeGroup> ageGroupList;
}
