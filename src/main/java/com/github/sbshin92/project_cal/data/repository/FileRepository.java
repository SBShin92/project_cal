package com.github.sbshin92.project_cal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sbshin92.project_cal.data.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    // 기본 CRUD 메서드는 JpaRepository에서 제공됩니다.
    
    // 필요한 경우 커스텀 쿼리 메서드를 여기에 추가할 수 있습니다.
    // 예: List<FileEntity> findByEventId(Long eventId);
}