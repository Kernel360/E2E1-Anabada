package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
	DEACTIVATE_MEMBER(403, "비활성화 상태 계정입니다."),
	NOT_FOUND_MEMBER(404, "존재하지 않는 회원 입니다.");

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
