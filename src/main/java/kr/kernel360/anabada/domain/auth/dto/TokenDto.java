package kr.kernel360.anabada.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
	@ApiModelProperty(value = "액세스 토큰", example = "access_token_example", required = true)
	private String accessToken;

	@ApiModelProperty(value = "리프레시 토큰", example = "refresh_token_example", required = true)
	private String refreshToken;
}
