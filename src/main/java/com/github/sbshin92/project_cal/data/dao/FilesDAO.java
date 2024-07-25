package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.sbshin92.project_cal.data.vo.FileVO;

@Mapper
public interface FilesDAO {
	
	
	@Select("SELECT file_id as fileId, "
			+ " 	project_id as projectId, "
			+ " 	task_id as taskId, "
			+ " 	file_name as fileName, "
			+ " 	original_file_name as originalFileName, "
			+ "		file_size as fileSize, "
			+ " 	uploaded_at as uploadedAt "
			+ " FROM files")
	public List<FileVO> findAll();
	
	@Select("SELECT file_id as fileId, "
			+ " 	project_id as projectId, "
			+ " 	task_id as taskId, "
			+ " 	file_name as fileName, "
			+ " 	original_file_name as originalFileName, "
			+ "		file_size as fileSize, "
			+ " 	uploaded_at as uploadedAt "
			+ " FROM files "
			+ " WHERE file_id = #{fileId} ")
	public FileVO findByFileId(int fileId);

	@Select("SELECT file_id as fileId, "
			+ " 	project_id as projectId, "
			+ " 	task_id as taskId, "
			+ " 	file_name as fileName, "
			+ " 	original_file_name as originalFileName, "
			+ "		file_size as fileSize, "
			+ " 	uploaded_at as uploadedAt "
			+ " FROM files "
			+ " WHERE project_id = #{projectId} ")
	public List<FileVO> findByProjectId(int projectId);
	
	@Select("SELECT file_id as fileId, "
			+ " 	project_id as projectId, "
			+ " 	task_id as taskId, "
			+ " 	file_name as fileName, "
			+ " 	original_file_name as originalFileName, "
			+ "		file_size as fileSize, "
			+ " 	uploaded_at as uploadedAt "
			+ " FROM files "
			+ " WHERE task_id = #{taskId} ")
	public FileVO findByTaskId(int taskId);
	
	
	@Insert("INSERT INTO files( "
			+ "			project_id, "
			+ "			task_id, "
			+ "			file_name, "
			+ "			original_file_name,"
			+ "			file_size) "
			+ " VALUES( "
			+ " 		#{projectId}, "
			+ "			#{taskId}, "
			+ "			#{fileName}, "
			+ "			#{originalFileName},"
			+ "			#{fileSize} "
			+ " )")
	public Integer save(FileVO fileVO);
	
	
}