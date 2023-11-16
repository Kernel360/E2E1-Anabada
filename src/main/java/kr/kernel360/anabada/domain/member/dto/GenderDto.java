package kr.kernel360.anabada.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GenderDto {
	@ApiModelProperty(value = "성별", example = "M")
	private String gender;

	@ApiModelProperty(value = "회원 수", example = "125")
	private Long count;

	@QueryProjection
	public GenderDto(String gender, Long count) {
		this.gender = gender;
		this.count = count;
	}
}
