package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TokenErrorCode implements ErrorCode {
	INVALID_TOKEN(401, "유효하지 않은 토큰 입니다."),
	EXPIRED_TOKEN(401, "만료된 토큰 입니다."),
	UNAUTHORIZED_TOKEN(401, "지원하지 않는 토큰 입니다."),
	WRONG_TOKEN(401, "잘못된 토큰 입니다."),
	EXPIRED_LOGIN_INFO(401, "로그인 정보가 만료 되었습니다.");

	private final int status;
	private final String message;

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
