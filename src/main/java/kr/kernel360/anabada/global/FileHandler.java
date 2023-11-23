package kr.kernel360.anabada.global;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.kernel360.anabada.global.error.code.FileErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileHandler {
	private final String imageStoragePath = "images/";
	public String parseFileInfo(MultipartFile multipartFile, String detailPath) {
		String contentType = findContentTypeByFile(multipartFile);
		if (!isValidImageType(contentType))
			return null;
		String uniqueImageName = generateUniqueImageName(multipartFile.getOriginalFilename());
		String imageSavePath = imageStoragePath + detailPath;
		String filePathString = generateImagePath(imageSavePath, uniqueImageName);
		transferTo(multipartFile, new File(filePathString));

		return "/images/" + detailPath + "/" + uniqueImageName;
	}
	private String findContentTypeByFile(MultipartFile multipartFile) {
		return Optional.ofNullable(multipartFile.getContentType())
			.orElseThrow(() -> new BusinessException(FileErrorCode.NOT_FOUND_FILE_TYPE));
	}

	private boolean isValidImageType(String contentType) {
		List<String> allowedImageExtensions = List.of("image/jpeg", "image/png");
		if (!allowedImageExtensions.contains(contentType)) {
			throw new BusinessException(FileErrorCode.UNSUPPORTED_EXTENSION);
		}
		return true;
	}

	private String generateUniqueImageName(String originalFilename) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"))
			+ "_" + originalFilename;
	}

	private String generateImagePath(String imageSavePath, String uniqueImageName) {
		File file = new File(imageSavePath, uniqueImageName).getAbsoluteFile();
		return file.toString().replace("/build/libs", "");
	}

	private void transferTo(MultipartFile multipartFile, File file) {
		try {
			multipartFile.transferTo(file);
		} catch (IOException e) {
			throw new BusinessException(FileErrorCode.FILE_SYSTEM_ERROR);
		}
	}

}