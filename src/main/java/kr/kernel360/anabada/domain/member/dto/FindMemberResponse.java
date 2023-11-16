package kr.kernel360.anabada.domain.member.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindMemberResponse {
	@ApiModelProperty(value = "회원 아이디", example = "1")
	private Long id;

	@ApiModelProperty(value = "이메일", example = "anabada@example.com")
	private String email;

	@ApiModelProperty(value = "닉네임", example = "봄동나물김민지")
	private String nickname;

	@ApiModelProperty(value = "성별", example = "F")
	private String gender;

	@ApiModelProperty(value = "생년월일", example = "1999-10-11")
	private String birth;

	@ApiModelProperty(value = "권한", example = "[\"ROLE_USER\"]", dataType = "string[]")
	private List<String> role;

	public static FindMemberResponse of(Member member) {

		return FindMemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.gender(member.getGender())
			.birth(member.getBirth())
			.role(Arrays.stream(member.getAuthorities()
				.split(","))
				.map(String::trim)
				.toList())
			.build();
	}

	@Builder
	public FindMemberResponse(Long id, String email, String nickname, String gender, String birth, List<String> role) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
		this.role = role;
	}
}
