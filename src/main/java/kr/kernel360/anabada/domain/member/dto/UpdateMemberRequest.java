package kr.kernel360.anabada.domain.member.dto;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "닉네임", example = "여름수박김민지")
	private String nickname;

	@ApiModelProperty(value = "성별", example = "F")
	private String gender;

	@ApiModelProperty(value = "생년월일", example = "2000-11-10")
	private String birth;
}
