package kr.kernel360.anabada.domain.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class FindAllMemberByGenderResponse {
	private List<GenderDto> genderList;

	public static FindAllMemberByGenderResponse of(List<GenderDto> genderList) {
		return FindAllMemberByGenderResponse.builder()
			.genderList(genderList)
			.build();
	}
}
