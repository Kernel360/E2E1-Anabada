package kr.kernel360.anabada.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.kernel360.anabada.global.jwt.JwtAccessDeniedHandler;
import kr.kernel360.anabada.global.jwt.JwtAuthenticationEntryPoint;
import kr.kernel360.anabada.global.jwt.JwtSecurityConfig;
import kr.kernel360.anabada.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
			.antMatchers("/error/**", "/assets/**", "/include/**", "/js/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();

		httpSecurity.authorizeRequests()
			.antMatchers(
				"/api/v1/members",
				"/auth/login.html",
				"/auth/signUp.html",
				"/api/v1/auth/isEmailUnique",
			    "/api/v1/auth/isNicknameUnique",
				"/api/v1/auth/authenticate",
				"/api/v1/auth/signUp",
				"/"
			).permitAll()
			.anyRequest().authenticated()

			.and()
			/** 401, 403 Exception 핸들링 **/
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)

			.and()
			/** 세션을 사용하지 않기 때문에 STATELESS로 설정 **/
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			/** JwtSecurityConfig 적용 **/
			.apply(new JwtSecurityConfig(tokenProvider));

		return httpSecurity.build();
	}
}
