package kr.kernel360.anabada.domain.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
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

	private final String authorities = "ROLE_USER";

	private final Boolean accountStatus = true;

	private final SocialProvider socialProvider = SocialProvider.LOCAL;

	private String ageGroup;

	public static Member toEntity(SignUpRequest signUpRequest) {
		return Member.builder()
			.email(signUpRequest.email)
			.nickname(signUpRequest.nickname)
			.password(signUpRequest.password)
			.ageGroup(signUpRequest.ageGroup)
			.authorities(signUpRequest.authorities)
			.gender(signUpRequest.gender)
			.birth(signUpRequest.birth)
			.socialProvider(signUpRequest.socialProvider)
			.accountStatus(signUpRequest.accountStatus)
			.build();
	}
}
