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
public class FileVO {
	private int fileId;
	private int projectId;
	private int taskId;
	private String fileName;
	private String originalFileName;
	private Timestamp updatedAt;
}
