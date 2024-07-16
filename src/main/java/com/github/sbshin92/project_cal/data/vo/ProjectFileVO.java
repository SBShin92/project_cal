package com.github.sbshin92.project_cal.data.vo;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectFileVO {
    private Integer fileId;
    private Integer projectId;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private LocalDateTime uploadDate;

   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectFileVO that = (ProjectFileVO) o;
        return Objects.equals(fileId, that.fileId) &&
               Objects.equals(projectId, that.projectId) &&
               Objects.equals(fileName, that.fileName) &&
               Objects.equals(filePath, that.filePath) &&
               Objects.equals(fileSize, that.fileSize) &&
               Objects.equals(uploadDate, that.uploadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, projectId, fileName, filePath, fileSize, uploadDate);
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