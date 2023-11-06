package kr.kernel360.anabada.domain.auth.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	private TokenResponse tokenResponse;

	public static LoginResponse of(String email, Collection<? extends GrantedAuthority> authorities, TokenResponse tokenResponse) {
		List<String> roles = authorities.stream()
			.map(String::valueOf)
			.toList();

		return LoginResponse.builder()
			.email(email)
			.roles(roles)
			.tokenResponse(tokenResponse)
			.build();
	}
}
