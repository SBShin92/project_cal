package com.github.sbshin92.project_cal.data.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}