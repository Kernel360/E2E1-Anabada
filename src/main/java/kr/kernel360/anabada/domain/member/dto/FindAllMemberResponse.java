package kr.kernel360.anabada.domain.member.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAllMemberResponse {
	List<FindMemberResponse> members;

	public static FindAllMemberResponse of(List<FindMemberResponse> members) {
		return FindAllMemberResponse.builder()
			.members(members)
			.build();
	}

	@Builder
	public FindAllMemberResponse(List<FindMemberResponse> members) {
		this.members = members;
	}
}
