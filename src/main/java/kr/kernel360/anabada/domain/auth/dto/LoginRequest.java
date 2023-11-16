package kr.kernel360.anabada.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@ApiModelProperty(value = "이메일", example = "anabada@example.com", required = true)
	private String email;

	@ApiModelProperty(value = "비밀번호", example = "password_example", required = true)
	private String password;
}

