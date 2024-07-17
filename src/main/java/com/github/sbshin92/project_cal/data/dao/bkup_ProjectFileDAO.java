//package com.github.sbshin92.project_cal.data.dao;
//
//import java.util.List;
//import java.util.Date;
//import org.apache.ibatis.annotations.*;
//import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
//
//@Mapper
//public interface ProjectFileDAO {
//
//    /**
//     * 새로운 프로젝트 파일 정보를 데이터베이스에 삽입합니다.
//     * @param projectFile 삽입할 프로젝트 파일 정보
//     * @return 삽입된 행의 수
//     */
//    @Insert("INSERT INTO project_files (project_id, file_name, file_path, file_size) " +
//            "VALUES (#{projectId}, #{fileName}, #{filePath}, #{fileSize})")
//    @Options(useGeneratedKeys = true, keyProperty = "fileId")
//    int insert(ProjectFileVO projectFile);
//
//    /**
//     * 특정 프로젝트에 속한 모든 파일 정보를 조회합니다.
//     * @param projectId 조회할 프로젝트의 ID
//     * @return 프로젝트에 속한 파일 정보 리스트
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files WHERE project_id = #{projectId}")
//    List<ProjectFileVO> findByProjectId(Integer projectId);
//
//    /**
//     * 특정 파일 ID에 해당하는 파일 정보를 조회합니다.
//     * @param fileId 조회할 파일의 ID
//     * @return 조회된 파일 정보
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files WHERE file_id = #{fileId}")
//    ProjectFileVO findById(Integer fileId);
//
//    /**
//     * 기존 파일 정보를 업데이트합니다.
//     * @param projectFile 업데이트할 파일 정보
//     * @return 업데이트된 행의 수
//     */
//    @Update("UPDATE project_files SET file_name = #{fileName}, file_path = #{filePath}, " +
//            "file_size = #{fileSize} WHERE file_id = #{fileId}")
//    int update(ProjectFileVO projectFile);
//
//    /**
//     * 특정 파일을 삭제합니다.
//     * @param fileId 삭제할 파일의 ID
//     * @return 삭제된 행의 수
//     */
//    @Delete("DELETE FROM project_files WHERE file_id = #{fileId}")
//    int delete(Integer fileId);
//
//    /**
//     * 특정 프로젝트에 속한 파일의 개수를 조회합니다.
//     * @param projectId 조회할 프로젝트의 ID
//     * @return 프로젝트에 속한 파일의 개수
//     */
//    @Select("SELECT COUNT(*) FROM project_files WHERE project_id = #{projectId}")
//    int countByProjectId(Integer projectId);
//
//    /**
//     * 프로젝트 ID와 파일 이름으로 특정 파일 정보를 조회합니다.
//     * @param projectId 조회할 프로젝트의 ID
//     * @param fileName 조회할 파일의 이름
//     * @return 조회된 파일 정보
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files " +
//            "WHERE project_id = #{projectId} AND file_name = #{fileName}")
//    ProjectFileVO findByProjectIdAndFileName(@Param("projectId") Integer projectId, @Param("fileName") String fileName);
//
//    /**
//     * 파일 경로로 특정 파일 정보를 조회합니다.
//     * @param filePath 조회할 파일의 경로
//     * @return 조회된 파일 정보
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files WHERE file_path = #{filePath}")
//    ProjectFileVO findByFilePath(String filePath);
//
//    /**
//     * 특정 크기 이상의 파일 목록을 조회합니다.
//     * @param projectId 조회할 프로젝트의 ID
//     * @param minSize 최소 파일 크기
//     * @return 조건에 맞는 파일 정보 리스트
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files " +
//            "WHERE project_id = #{projectId} AND file_size >= #{minSize}")
//    List<ProjectFileVO> findFilesByMinimumSize(@Param("projectId") Integer projectId, @Param("minSize") Long minSize);
//
//    /**
//     * 특정 날짜 이후에 업로드된 파일 목록을 조회합니다.
//     * @param projectId 조회할 프로젝트의 ID
//     * @param date 기준 날짜
//     * @return 조건에 맞는 파일 정보 리스트
//     */
//    @Select("SELECT file_id as fileId, project_id as projectId, file_name as fileName, " +
//            "file_path as filePath, file_size as fileSize, " +
//            "upload_date as uploadDate FROM project_files " +
//            "WHERE project_id = #{projectId} AND upload_date > #{date}")
//    List<ProjectFileVO> findFilesByUploadDateAfter(@Param("projectId") Integer projectId, @Param("date") Date date);
//
//    /**
//     * 특정 프로젝트의 모든 파일을 삭제합니다.
//     * @param projectId 파일을 삭제할 프로젝트의 ID
//     * @return 삭제된 행의 수
//     */
//    @Delete("DELETE FROM project_files WHERE project_id = #{projectId}")
//    int deleteAllByProjectId(Integer projectId);
//}


