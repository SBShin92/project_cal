package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.vo.FileVO;

public interface FileService {
	public List<FileVO> getFileListByProjectId(Integer projectId);
	public boolean saveFilesInProject(MultipartFile[] multipartFiles, Integer projectId) throws IOException;
    

    
//    void deleteFile(String filePath) throws IOException;
//    Path load(String filename);
//    Resource loadAsResource(String filename) throws IOException;
//    void init() throws IOException;
//    String getUploadDir();

}