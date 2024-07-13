	package com.github.sbshin92.project_cal.data.vo;

	import lombok.AllArgsConstructor;
	import lombok.Builder;
import lombok.Data;
import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;	

	@Data
	public class UsersTasksVO {
		private int userId;
		private String userName;
		private int taskId;
		private int projectId;
		private Date createdAt;

	}
	
