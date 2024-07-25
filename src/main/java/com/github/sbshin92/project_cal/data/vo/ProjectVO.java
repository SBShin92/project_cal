package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer userId;
    private String projectTitle;
    private String projectDescription;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String projectStatus;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
  
    // 조인용 필드
    private String userName;
    private String userPosition; //0725
}
