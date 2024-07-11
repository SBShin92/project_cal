package com.github.sbshin92.project_cal.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Failed to store empty file");
        }
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path destinationFile = Paths.get(uploadDir).resolve(Paths.get(fileName)).normalize().toAbsolutePath();
        
        if (!destinationFile.getParent().equals(Paths.get(uploadDir).toAbsolutePath())) {
            throw new IOException("Cannot store file outside current directory.");
        }
        
        try {
            Files.copy(file.getInputStream(), destinationFile);
        } catch (IOException e) {
            throw new IOException("Failed to store file " + fileName, e);
        }
        
        logger.info("File saved: {}", destinationFile);
        return destinationFile.toString();
    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                logger.info("File deleted: {}", filePath);
            } else {
                throw new IOException("Failed to delete file: " + filePath);
            }
        } else {
            logger.warn("File not found: {}", filePath);
        }
    }
}