package kr.kernel360.anabada.domain.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.domain.SocialProvider;
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
public class SignUpRequest {
	@NotBlank
	@Email
	@ApiModelProperty(value = "이메일", example = "anabada@example.com", required = true)
	private String email;

	@NotBlank
	@Size(min = 4, max = 10)
	@ApiModelProperty(value = "닉네임", example = "user123", required = true)
	private String nickname;

	@NotBlank
	@Size(min = 8, max = 16)
	@ApiModelProperty(value = "비밀번호", example = "password_example", required = true)
	private String password;

	@NotBlank
	@ApiModelProperty(value = "성별", example = "M", required = true)
	private String gender;

	@NotBlank
	@ApiModelProperty(value = "생년월일", example = "1991-10-10", required = true)
	private String birth;

	@ApiModelProperty(value = "권한", example = "ROLE_USER")
	private final String authorities = "ROLE_USER";

	@JsonProperty(value = "account_status")
	@ApiModelProperty(value = "계정 상태", example = "true")
	private final Boolean accountStatus = true;

	@JsonProperty(value = "social_provider")
	@ApiModelProperty(value = "소셜 계정 정보 제공자 \n LOCAL이면 기본 회원가입을 통해 가입한 계정"
		, example = "LOCAL")
	private final SocialProvider socialProvider = SocialProvider.LOCAL;

	@JsonProperty(value = "age_group")
	@ApiModelProperty(value = "연령대", example = "90년대생")
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
