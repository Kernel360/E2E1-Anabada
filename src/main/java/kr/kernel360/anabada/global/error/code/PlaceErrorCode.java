package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlaceErrorCode implements ErrorCode {
	NOT_FOUND_PLACE(404, "존재하지 않는 장소입니다.");

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