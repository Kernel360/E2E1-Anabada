package kr.kernel360.anabada.global.error.dto;

import kr.kernel360.anabada.global.error.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {
	private String message;
	private int status;

	@Builder
	public ErrorResponse(String message, int status) {
		this.message = message;
		this.status = status;
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return ErrorResponse.builder()
			.message(errorCode.getMessage())
			.status(errorCode.getStatus())
			.build();
	}
}
