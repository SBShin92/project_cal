package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class ProjectVO {
    private int projectId;
    private int userId;
    private String projectTitle;
    private String projectDescription;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String projectStatus;
    private Date startDate;
    private Date endDate;
    private List<MultipartFile> files;
}