package kr.kernel360.anabada.domain.member.dto;

import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindMemberResponse {
	private Long id;

	private String email;

	private String nickname;

	private String gender;

	private String birth;

	public static FindMemberResponse of(Member member) {
		return FindMemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.gender(member.getGender())
			.birth(member.getBirth())
			.build();
	}

	@Builder
	public FindMemberResponse(Long id, String email, String nickname, String gender, String birth) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
	}
}
