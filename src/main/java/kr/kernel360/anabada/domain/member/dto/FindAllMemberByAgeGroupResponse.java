package kr.kernel360.anabada.domain.member.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllMemberByAgeGroupResponse {
	private List<AgeGroupDto> ageGroupList;

	public static FindAllMemberByAgeGroupResponse of(List<AgeGroupDto> ageGroupList) {
		return FindAllMemberByAgeGroupResponse.builder()
			.ageGroupList(ageGroupList)
			.build();
	}
}
