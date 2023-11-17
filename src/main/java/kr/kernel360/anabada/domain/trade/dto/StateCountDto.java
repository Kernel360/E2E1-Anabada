package kr.kernel360.anabada.domain.trade.dto;

import com.querydsl.core.annotations.QueryProjection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StateCountDto {
	@ApiModelProperty(value = "시, 도", name = "서울특별시")
	private String state;

	@ApiModelProperty(value = "회원 수", name = "50")
	private Long count;

	@QueryProjection
	public StateCountDto(String state, Long count) {
		this.state = state;
		this.count = count;
	}
}
