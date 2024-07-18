package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

/**
 * 프로젝트 관련 데이터베이스 작업을 처리하는 DAO 인터페이스입니다. MyBatis의 @Mapper 어노테이션을 사용하여 자동으로 구현체를
 * 생성합니다.
 */
@Mapper
public interface ProjectsDAO {

	/**
	 * 모든 프로젝트를 조회합니다.
	 * 
	 * @return 모든 프로젝트 목록
	 */
	@Select("SELECT * FROM projects")
	List<ProjectVO> findAll();

	/**
	 * 특정 ID의 프로젝트를 조회합니다.
	 * 
	 * @param projectId 조회할 프로젝트의 ID
	 * @return 조회된 프로젝트 정보
	 */
//	@Select("SELECT project_id as projectId, " + " user_id as userId, " + " project_title as projectTitle,"
//			+ " project_description as projectDescription," + " project_status as projectStatus, "
//			+ " start_date as startDate, " + " end_date as endDate " + " FROM projects WHERE project_id = #{projectId}")
//	ProjectVO findById(@Param("projectId") int projectId);

	   @Select("SELECT project_id as projectId, user_id as userId, project_title as projectTitle, " +
	            "project_description as projectDescription, project_status as projectStatus, " +
	            "start_date as startDate, end_date as endDate, project_bar_color as projectBarColor " +
	            "FROM projects WHERE project_id = #{projectId}")
	    ProjectVO findById(@Param("projectId") int projectId);
	// TODO userId 꼭 바꿔야함
	/**
	 * 새 프로젝트를 데이터베이스에 삽입합니다.
	 * 
	 * @param project 삽입할 프로젝트 정보
	 * @return 삽입된 행의 수
	 */
//    @Insert("INSERT INTO projects  (user_id, project_title, project_description, start_date, end_date) " +
//            "VALUES ((select u.user_id FROM projects as p INNER JOIN users as u ON u.user_id = p.user_id)"
//            + " #{userId}, #{projectTitle}, #{projectDescription}, #{startDate}, #{endDate}) "
//    		)
//    @Options(useGeneratedKeys = true, keyProperty = "projectId")
//    int insert(ProjectVO project);


	@Insert("INSERT INTO projects (user_id, project_title, project_description, start_date, end_date, project_bar_color) "
	        + " VALUES (#{userId}, #{projectTitle}, #{projectDescription}, #{startDate}, #{endDate}, FLOOR(0 + RAND() * (16581375 - 0 + 1)))")
	@Options(useGeneratedKeys = true, keyProperty = "projectId")
	int insert(ProjectVO project);

	/**
	 * 기존 프로젝트 정보를 업데이트합니다.
	 * 
	 * @param project 업데이트할 프로젝트 정보
	 * @return 업데이트된 행의 수
	 */
//	@Update("UPDATE projects SET project_title = #{projectTitle}, project_description = #{projectDescription}, "
//			+ "start_date = #{startDate}, end_date = #{endDate}, "
//			+ "project_status = #{projectStatus} WHERE project_id = #{projectId}")
//	int update(ProjectVO project);
	   @Update("UPDATE projects SET project_title = #{projectTitle}, project_description = #{projectDescription}, " +
	            "start_date = #{startDate}, end_date = #{endDate}, " +
	            "project_status = #{projectStatus}, project_bar_color = #{projectBarColor} WHERE project_id = #{projectId}")
	    int update(ProjectVO project);


	/**
	 * 특정 ID의 프로젝트를 삭제합니다.
	 * 
	 * @param projectId 삭제할 프로젝트의 ID
	 * @return 삭제된 행의 수
	 */
	@Delete("DELETE FROM projects WHERE project_id = #{projectId}")
	int delete(@Param("projectId") int projectId);

	/**
	 * 프로젝트에 파일 정보를 삽입합니다.
	 * 
	 * @param projectId 파일이 속한 프로젝트의 ID
	 * @param fileName  파일 이름
	 * @param filePath  파일 경로
	 * @param fileSize  파일 크기
	 * @return 삽입된 행의 수
	 */
//    @Insert("INSERT INTO project_files (project_id, file_name, file_path, file_size) " +
//            "VALUES (#{projectId}, #{fileName}, #{filePath}, #{fileSize})")
//    int insertFile(@Param("projectId") int projectId, @Param("fileName") String fileName, 
//                   @Param("filePath") String filePath, @Param("fileSize") long fileSize);

	 @Insert("INSERT INTO project_files (project_id, file_name, file_path, file_size) " +
	            "VALUES (#{projectId}, #{fileName}, #{filePath}, #{fileSize})")
	    int insertFile(@Param("projectId") int projectId, @Param("fileName") String fileName, 
	                   @Param("filePath") String filePath, @Param("fileSize") long fileSize);

	    // 파일
	    @Select("SELECT file_path FROM project_files WHERE project_id = #{projectId}")
	    List<String> getProjectFilePaths(@Param("projectId") int projectId);
	    
	    int deleteFile(int fileId);
	    
	    /**
		 * 특정 사용자가 특정 프로젝트의 멤버인지 확인합니다.
		 * 
		 * @param userId    확인할 사용자의 ID
		 * @param projectId 확인할 프로젝트의 ID
		 * @return 사용자가 프로젝트 멤버이면 true, 아니면 false
		 */
	    
	    // 프로젝트에 들어있는 멤버조회?
	    @Select("SELECT user_id as userId FROM users u JOIN user_project up ON u.user_id = up.user_id WHERE up.project_id = #{projectId}")
	    List<UserVO> getProjectMembers(@Param("userId") Integer userId);
	   
	    // 등록된 멤버인지 확인
	    @Select("SELECT COUNT(*) > 0 FROM projects_users WHERE user_id = #{userId} AND project_id = #{projectId}")
	    public boolean isUserProjectMember(@Param("userId") int userId, @Param("projectId") int projectId);
	    
	    @Insert("INSERT INTO projects_users(user_id, project_id) " +
	            "VALUES (#{userId}, #{projectId})")
	    int addMemberProject(@Param("userId") int userId, @Param("projectId") int projectId);

	    @Delete("DELETE FROM projects_users WHERE user_id = #{userId} AND project_id = #{projectId}")
	    int deleteProjectUser(@Param("userId") int userId, @Param("projectId") int projectId);

	    @Select("SELECT u.user_id as userId, u.user_name as userName, u.user_email as userEmail, " +
	            "u.user_password as userPassword, u.user_authority as userAuthority, " +
	            "u.user_position as userPosition, u.created_at as createdAt, u.updated_at as updatedAt, " +
	            "u.can_create_project as canCreateProject, " +
	            "p.project_id as projectId, p.project_title as projectTitle, p.project_description as projectDescription, " +
	            "p.created_at as projectCreatedAt, p.updated_at as projectUpdatedAt, p.project_status as projectStatus, " +
	            "p.project_bar_color as projectBarColor, p.start_date as startDate, p.end_date as endDate " +
	            "FROM users u " +
	            "JOIN projects_users pu ON u.user_id = pu.user_id " +
	            "JOIN projects p ON pu.project_id = p.project_id " +
	            "WHERE pu.project_id = #{projectId}")
	    List<UserVO> getAllUsers(@Param("projectId") int projectId);
	    
//	    // 멤버 추가
//	    @Insert("INSERT INTO projects_users(user_id, project_id)" +
//	            "SELECT u.user_id, p.project_id" +
//	            "FROM userVO u" +
//	            "JOIN projectVO p ON u.user_id = p.user_id" +
//	            "WHERE u.user_id = #{userId}")
//		public int addMemberProject(@Param("userId") int userId, @Param("projectId") int projectId);
//		
//		// 멤버 삭제
//		@Delete("DELETE FROM projects_users WHERE user_id = #{userId} AND project_id=#{projectId}")
//		public int deleteProjectUser(@Param("userId") int userId, @Param("projectId") int projectId);
//		
//		// 전체 멤버 조회
//		@Select("SELECT u.user_id as userId, u.user_name as userName, u.user_email as userEmail, " +
//	            "u.user_password as userPassword, u.user_authority as userAuthority, " +
//	            "u.user_position as userPosition, u.created_at as createdAt, u.updated_at as updatedAt, " +
//	            "u.can_create_project as canCreateProject, p.project_id as projectId, p.project_name as projectName " +
//	            "FROM users u " +
//	            "JOIN projects_users pu ON u.user_id = pu.user_id " +
//	            "JOIN projects p ON pu.project_id = p.project_id " +
//	            "WHERE pu.project_id = #{projectId}")
//		    List<UserVO> getAllUsers();
}
	
//	@Select("SELECT COUNT(*) > 0 FROM user_project WHERE user_id = #{userId} AND project_id = #{projectId}")
//	boolean isUserProjectMember(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

//	/**
//	 * 특정 프로젝트의 모든 멤버를 조회합니다.
//	 * 
//	 * @parwam projectId 멤버를 조회할 프로젝트의 ID
//	 * @return 프로젝트 멤버 목록
//	 */
//	@Select("SELECT u.* FROM users u JOIN user_project up ON u.user_id = up.user_id WHERE up.project_id = #{projectId}")
//	List<UserVO> getProjectMembers(@Param("projectId") Integer projectId);
		
//	/**
//	 * 특정 프로젝트의 모든 파일 경로를 조회합니다.
//	 * 
//	 * @param projectId 파일 경로를 조회할 프로젝트의 ID
//	 * @return 프로젝트 파일 경로 목록
//	 */
//	List<String> getProjectFilePaths(int projectId);
//}
