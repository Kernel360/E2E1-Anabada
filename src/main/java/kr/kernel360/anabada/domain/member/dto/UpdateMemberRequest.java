package kr.kernel360.anabada.domain.member.dto;

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
public class UpdateMemberRequest {
	private Long id;

	private String email;

	private String nickname;

	private String password;

	private String gender;

	private String birth;
}
