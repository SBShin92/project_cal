package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserVO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAuthority;
    private String userPosition;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}