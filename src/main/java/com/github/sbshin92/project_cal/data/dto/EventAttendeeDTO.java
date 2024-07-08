package com.github.sbshin92.project_cal.data.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventAttendeeDTO {
    private Long attendeeId;
    private Long eventId;
    private Long userId;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}