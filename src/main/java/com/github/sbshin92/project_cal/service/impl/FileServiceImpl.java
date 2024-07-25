package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

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
	
	@Autowired
	private FilesUtility filesUtility;

	
    @Override
	public List<FileVO> getFileListByProjectId(Integer projectId) {
		return filesDAO.findByProjectId(projectId);
	}



	@Override
    public boolean saveFilesInProject(MultipartFile[] multipartFiles, Integer projectId) throws IOException {
    	boolean isSuccess = true;
    	if (multipartFiles[0].isEmpty())
    		return isSuccess;
		for (MultipartFile file: multipartFiles) {
			// 각 파일명 구하기
			String originalFileName = file.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
			String saveFileName = filesUtility.getFileNameByTimeMillis(extName);
			// 파일 사이즈
			Long fileSize = file.getSize();
			
			// 로컬에 파일 저장
			filesUtility.writeFile(file, saveFileName);
			// 저장 정보 DB에 저장
			FileVO fileVO = FileVO.builder()
								.projectId(projectId)
								.fileName(saveFileName)
								.originalFileName(originalFileName)
								.fileSize(fileSize)
								.build();
			if (1 != filesDAO.save(fileVO)) {
				isSuccess = false;
				break ;
			}
		}
    	return isSuccess;
    }

	@Override
	public FileVO getFileById(Integer fileId) {
		
		return filesDAO.findByFileId(fileId);
	}
	
}