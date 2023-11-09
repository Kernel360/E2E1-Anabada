package kr.kernel360.anabada.domain.auth.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refreshToken", timeToLive = 60)
public class RefreshToken {
	@Id
	private String refreshToken;

	private String email;

	private LocalDateTime createdDate;

	private LocalDateTime expirationDate;

	@Builder
	public RefreshToken(String refreshToken, String email, LocalDateTime createdDate, LocalDateTime expirationDate) {
		this.refreshToken = refreshToken;
		this.email = email;
		this.createdDate = createdDate;
		this.expirationDate = expirationDate;
	}
}
