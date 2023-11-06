package kr.kernel360.anabada.global;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileHandler {
	public String parseFileInfo(MultipartFile multipartFile) {

		// jpeg, png 파일들만 받아서 처리할 예정
		String contentType = multipartFile.getContentType();
		if (!(contentType.contains("image/jpg") || contentType.contains("image/jpeg") || contentType.contains("image/png"))) {
			return null;
		}

		// String imageName = System.nanoTime()+"_"+multipartFile.getOriginalFilename();
		String imageName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH24mmss")) + "_" + multipartFile.getOriginalFilename();

		// 경로를 지정하고 그곳에다가 저장

		String imagePath = "images/";
		File file = new File(imagePath, imageName).getAbsoluteFile();


		// 저장된 파일로 변경하여 이를 보여주기 위함
		try {
			multipartFile.transferTo(file);
		} catch (IOException e) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("파일시스템 처리 중 에러가 발생하였습니다.");
		}

		return "/images/"+imageName;
	}

}