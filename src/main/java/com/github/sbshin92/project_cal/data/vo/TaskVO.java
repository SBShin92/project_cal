package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class TaskVO {
	private int taskId;
    private int userId;
    private String userName;//0725 추가함
    private String userPosition;//0725 추가함
    private int projectId;
    private String taskTitle;
    private String taskDescription;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String taskStatus;
  
    //추가함 삭제 금지 0723
    private int page; // 페이지 세팅용 
}

//수정함 0718