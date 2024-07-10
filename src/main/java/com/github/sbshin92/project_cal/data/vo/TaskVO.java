package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
