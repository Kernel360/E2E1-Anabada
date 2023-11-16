package kr.kernel360.anabada.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgeGroupDto {
	@JsonProperty(value = "age_group")
	@ApiModelProperty(value = "연령대", name = "90년대생")
	private String ageGroup;

	@ApiModelProperty(value = "회원 수", name = "50")
	private Long count;

	@QueryProjection
	public AgeGroupDto(String ageGroup, Long count) {
		this.ageGroup = ageGroup;
		this.count = count;
	}
}
