package kr.kernel360.anabada.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
public class KakaoFeignConfiguration {
	@Bean
	public Client feingClient() {
		return new Client.Default(null, null);
	}
}
