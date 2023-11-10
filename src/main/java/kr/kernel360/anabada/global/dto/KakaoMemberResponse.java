package kr.kernel360.anabada.global.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoMemberResponse {
	private String email;

	private String gender;

	private String birthday;
}
