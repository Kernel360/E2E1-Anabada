package kr.kernel360.anabada.global.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgeGroupParser {
	public static String birthToAgeGroup(String birth) {
		return String.valueOf(birth.charAt(2)) + "0년대생";
	}
}
