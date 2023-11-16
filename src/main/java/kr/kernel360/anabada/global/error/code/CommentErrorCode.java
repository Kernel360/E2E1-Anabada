package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommentErrorCode implements ErrorCode {
	NOT_FOUND_MEMBER(404, "존재하지 않는 회원 입니다."),
	NOT_FOUND_TRADE(404, "교환 정보를 찾을 수 없습니다.");

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