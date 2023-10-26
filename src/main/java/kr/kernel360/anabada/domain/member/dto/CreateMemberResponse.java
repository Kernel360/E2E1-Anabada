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
public class CreateMemberResponse {
	private Long id;

	private String email;

	private String nickname;

	public static CreateMemberResponse toDto(Member member) {
		return CreateMemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.build();
	}
}
