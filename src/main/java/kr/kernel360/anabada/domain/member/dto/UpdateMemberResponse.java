package kr.kernel360.anabada.domain.member.dto;

import kr.kernel360.anabada.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberResponse {
	private Long id;

	private String email;

	private String nickname;

	private String gender;

	private String birth;

	public static UpdateMemberResponse toDto(Member member) {
		return UpdateMemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.gender(member.getGender())
			.birth(member.getBirth())
			.build();
	}
}
