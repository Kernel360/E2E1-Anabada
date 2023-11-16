package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {
	INVALID_ACCOUNT(401, "계정정보가 일치하지 않습니다."),
	INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
	ALREADY_SAVED_NICKNAME(409, "사용 중인 닉네임입니다."),
	ALREADY_SAVED_EMAIL(409, "사용 중인 이메일입니다."),
	ALREADY_SOCIAL_LOGIN(409, "소셜로 가입한 회원입니다. 소셜로 로그인 해주세요.");

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
