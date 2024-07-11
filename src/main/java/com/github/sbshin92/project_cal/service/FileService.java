package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file) throws IOException;
    void deleteFile(String filePath) throws IOException;
}