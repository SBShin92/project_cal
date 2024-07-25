package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class TaskVO {
	private int taskId;
    private int userId;
    private int projectId;
    private String taskTitle;
    private String taskDescription;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String taskStatus;
  
    //조인용 필드 및 추가
    private String userName;//0725
    private String userPosition;//0725
    private int page; // 페이지 세팅용 
}

