
package com.github.sbshin92.project_cal.data.dao;

import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectFileDAO {
    
    // 파일 정보 삽입
    int insert(ProjectFileVO projectFile);
    
    // 프로젝트 ID로 파일 목록 조회
    List<ProjectFileVO> findByProjectId(Integer projectId);
    
    // 파일 ID로 파일 정보 조회
    ProjectFileVO findById(Integer fileId);
    
    // 파일 정보 업데이트
    int update(ProjectFileVO projectFile);
    
    // 파일 삭제
    int delete(Integer fileId);
    
    // 프로젝트에 속한 파일 개수 조회
    int countByProjectId(Integer projectId);
    
    // 프로젝트 ID와 파일 이름으로 파일 조회
    ProjectFileVO findByProjectIdAndFileName(@Param("projectId") Integer projectId, @Param("fileName") String fileName);
}