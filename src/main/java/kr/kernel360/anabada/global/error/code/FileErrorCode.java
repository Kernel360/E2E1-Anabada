package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum FileErrorCode implements ErrorCode {
	FILE_SYSTEM_ERROR(500, "파일시스템 처리 중 에러가 발생하였습니다."),
	UNSUPPORTED_EXTENSION(415, "지원하지 않는 파일 확장자 입니다."),
	NOT_FOUND_FILE_TYPE(422, "파일 타입을 찾을 수 없습니다.");

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