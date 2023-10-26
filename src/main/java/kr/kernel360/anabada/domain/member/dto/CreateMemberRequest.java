package kr.kernel360.anabada.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.kernel360.anabada.domain.member.entity.Member;
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

	public static Member toEntity(CreateMemberRequest createMemberRequest) {
		return Member.builder()
			.email(createMemberRequest.email)
			.nickname(createMemberRequest.nickname)
			.password(createMemberRequest.password)
			.authority(createMemberRequest.authority)
			.gender(createMemberRequest.gender)
			.birth(createMemberRequest.birth)
			.socialProvider(createMemberRequest.socialProvider)
			.accountStatus(createMemberRequest.accountStatus)
			.build();
	}
}
