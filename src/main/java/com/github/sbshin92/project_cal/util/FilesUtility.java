package com.github.sbshin92.project_cal.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 밀리초 파일이름을 얻고싶으면 getFileNameByTimeMillis(".확장자") 파일을 쓰고 싶으면 writeFile(멀티파트,
 * 위에서 얻은 파일이름)
 */

@Component
public class FilesUtility {
	
	@Value("${file.upload-dir}")
	private String FILE_PATH;

	private FilesUtility() {
	}

	public String getFileNameByTimeMillis(String extName) {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.getTimeInMillis()) + extName.toLowerCase();

	}

	public void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {

		byte[] fileData = multipartFile.getBytes();
		Path filePath = Paths.get(FILE_PATH, saveFileName);
		Files.write(filePath, fileData);

		// This is a Unix, Linux or Mac system.
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
			Set<PosixFilePermission> perms = new HashSet<>();
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);

			Files.setPosixFilePermissions(filePath, perms);
		}

	}

}