package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoryErrorCode implements ErrorCode {
	NOT_FOUND_CATEGORY(404, "존재하지 않는 카테고리 입니다."),
	ALREADY_SAVED_CATEGORY(409, "동일한 카테고리가 존재합니다.");

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
