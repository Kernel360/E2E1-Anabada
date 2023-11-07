package kr.kernel360.anabada.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindAllMemberByGenderResponse {
	private Long male;

	private Long female;
}
