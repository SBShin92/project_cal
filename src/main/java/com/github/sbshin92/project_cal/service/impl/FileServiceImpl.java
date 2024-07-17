package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.dao.FilesDAO;
import com.github.sbshin92.project_cal.data.vo.FileVO;
import com.github.sbshin92.project_cal.service.FileService;
import com.github.sbshin92.project_cal.util.FilesUtility;


@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FilesDAO filesDAO;

    @Override
    public boolean saveFilesInProject(MultipartFile[] multipartFiles, Integer projectId) throws IOException {
    	boolean isSuccess = true;
		for (MultipartFile file: multipartFiles) {
			// 각 파일명 구하기
			String originalFileName = file.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
			String saveFileName = FilesUtility.getFileNameByTimeMillis(extName);
			
			// 로컬에 파일 저장
			FilesUtility.writeFile(file, saveFileName);
			
			// 저장 정보 DB에 저장
			FileVO fileVO = FileVO.builder()
								.projectId(projectId)
								.fileName(saveFileName)
								.originalFileName(originalFileName)
								.build();
			if (1 != filesDAO.save(fileVO)) {
				isSuccess = false;
				break ;
			}
		}
    	return isSuccess;
    } 
}