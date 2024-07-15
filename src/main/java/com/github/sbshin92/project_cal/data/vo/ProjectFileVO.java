package com.github.sbshin92.project_cal.data.vo;

import java.time.LocalDateTime;

public class ProjectFileVO {
    private Integer fileId;
    private Integer projectId;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private LocalDateTime uploadDate;

    // Constructors
    public ProjectFileVO() {}

    public ProjectFileVO(Integer projectId, String fileName, String filePath, Long fileSize) {
        this.projectId = projectId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Getters and Setters
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "ProjectFileVO{" +
                "fileId=" + fileId +
                ", projectId=" + projectId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", uploadDate=" + uploadDate +
                '}';
    }
}