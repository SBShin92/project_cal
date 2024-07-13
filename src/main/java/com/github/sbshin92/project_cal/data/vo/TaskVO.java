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
}

//여기서 수정한것 없음