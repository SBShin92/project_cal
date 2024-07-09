package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventCommentVO {
    private Long commentId;
    private Long eventId;
    private Long userId;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}