package kr.kernel360.anabada.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TradeErrorCode implements ErrorCode {
	//HttpStatusCode
	NOT_FOUND_FILE_PATH(404, "파일 경로를 찾을 수 없습니다."),
	NOT_FOUND_MEMBER(404, "존재하지 않는 회원 입니다."),
	NOT_FOUND_CATEGORY(404, "존재하지 않는 카테고리 입니다."),
	NOT_FOUND_TRADE(404, "교환 정보를 찾을 수 없습니다."),
	NOT_FOUND_PLACE(404, "위치정보를 찾을 수 없습니다.");


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
