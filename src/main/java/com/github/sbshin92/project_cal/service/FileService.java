package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	

	
	public boolean saveFilesInProject(MultipartFile[] multipartFiles, Integer projectId) throws IOException;
    
    
    
    
    
    
    
    
//    void deleteFile(String filePath) throws IOException;
//    Path load(String filename);
//    Resource loadAsResource(String filename) throws IOException;
//    void init() throws IOException;
//    String getUploadDir();

}