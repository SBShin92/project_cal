package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileVO {
    private Long fileId;
    private Long eventId;
    private String fileName;
    private String filePath;
    private Timestamp uploadedAt;
}