package kr.kernel360.anabada.domain.auth.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String email;
	private List<String> roles;
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
