package kr.kernel360.anabada.global;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileHandler {
	public String parseFileInfo(MultipartFile multipartFile, String detailPath) {
		String contentType = Optional.ofNullable(multipartFile.getContentType())
			.orElseThrow(() -> new IllegalArgumentException("파일 타입을 찾을 수 없습니다."));
		if (!(contentType.contains("image/jpeg") || contentType.contains("image/png"))) {
			return null;
		}
		String imageName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH24mmss")) + "_" + multipartFile.getOriginalFilename();
		String imagePath = "src/main/resources/static/images/"+ detailPath;
		File file = new File(imagePath, imageName).getAbsoluteFile();

		try {
			multipartFile.transferTo(file);
		} catch (IOException e) {
			// todo : 추후 exception 타입 변경 필요
			throw new IllegalArgumentException("파일시스템 처리 중 에러가 발생하였습니다.");
		}

		return "/images/" + detailPath + "/" + imageName;
	}

}