package kr.kernel360.anabada.domain.auth.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	@ApiModelProperty(value = "이메일", example = "anabada@example.com")
	private String email;

	@JsonProperty(value = "roles")
	@ApiModelProperty(value = "권한", example = "[\"ROLE_USER\"]", dataType = "string[]")
	private List<String> roles;

	@JsonProperty(value = "token_dto")
	@ApiModelProperty
	private TokenDto tokenDto;

	public static LoginResponse of(String email, Collection<? extends GrantedAuthority> authorities, TokenDto tokenDto) {
		List<String> roles = authorities.stream()
			.map(String::valueOf)
			.toList();

		return LoginResponse.builder()
			.email(email)
			.roles(roles)
			.tokenDto(tokenDto)
			.build();
	}
}
