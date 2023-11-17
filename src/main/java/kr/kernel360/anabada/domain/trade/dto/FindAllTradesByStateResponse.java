package kr.kernel360.anabada.domain.trade.dto;

import java.util.List;

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
public class FindAllTradesByStateResponse {
	List<StateCountDto> states;

	public static FindAllTradesByStateResponse of(List<StateCountDto> states) {
		return FindAllTradesByStateResponse.builder()
			.states(states)
			.build();
	}
}

