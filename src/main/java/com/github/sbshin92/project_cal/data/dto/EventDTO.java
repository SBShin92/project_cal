package com.github.sbshin92.project_cal.data.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDTO {
    private Long eventId;
    private Long userId;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String repeatType;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String status;
    private Integer authority;
}