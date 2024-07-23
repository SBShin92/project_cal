package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
    private int taskPriority;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    //추가함 삭제 금지 0723
    private int page; // 페이지 세팅용 
}

//수정함 0718