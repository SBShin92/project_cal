	package com.github.sbshin92.project_cal.data.vo;

	import lombok.AllArgsConstructor;
	import lombok.Builder;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	import java.util.Date;	

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class UsersTasksVO {
		private int userId;
		private int taskId;
		private int projectId;
		private Date createdAt;

	}
	
	//UsersTasksVO.java 새로 추가함