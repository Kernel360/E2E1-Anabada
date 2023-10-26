package kr.kernel360.anabada.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMemberRequest{
	@Email
	@NotBlank
	private String email;

	@Size(min = 4, max = 40)
	@NotBlank
	private String nickname;

	@Size(min = 8, max = 16)
	@NotBlank
	private String password;

	private final String authority = "USER_ROLE";

	@NotBlank
	private String gender;

	@NotBlank
	private String birth;

	private final String socialProvider = "N";

	private final Boolean accountStatus = true;

	private Boolean locationPermission;

	@Builder
	public CreateMemberRequest(String email, String nickname, String password, String gender, String birth,
		Boolean locationPermission) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
		this.locationPermission = locationPermission;
	}

	public Member toEntity() {
		return Member.builder()
			.email(email)
			.nickname(nickname)
			.password(password)
			.authority(authority)
			.gender(gender)
			.birth(birth)
			.socialProvider(socialProvider)
			.accountStatus(accountStatus)
			.build();
	}
}
