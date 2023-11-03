package kr.kernel360.anabada.domain.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	@Email
	@NotBlank
	private String email;

	@Size(min = 4, max = 10)
	@NotBlank
	private String nickname;

	@NotBlank
	@Size(min = 8, max = 16)
	private String password;

	@NotBlank
	private String gender;

	@NotBlank
	private String birth;

	private final String authorities = "USER_ROLE";

	private final Boolean accountStatus = true;

	private final String socialProvider = "N";

	public static Member toEntity(SignupRequest signupRequest) {
		return Member.builder()
			.email(signupRequest.email)
			.nickname(signupRequest.nickname)
			.password(signupRequest.password)
			.authorities(signupRequest.authorities)
			.gender(signupRequest.gender)
			.birth(signupRequest.birth)
			.socialProvider(signupRequest.socialProvider)
			.accountStatus(signupRequest.accountStatus)
			.build();
	}
}
