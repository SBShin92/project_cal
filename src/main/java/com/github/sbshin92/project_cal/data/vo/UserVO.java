package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserVO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAuthority;
    private String userPosition;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String token;
    private RoleVO roleVO;  // RoleVO와의 관계를 나타내는 필드
    
    private Timestamp tokenExpiryDate; //비밀번호 리셋 시 토큰 유효시간
    /**
     * 사용자가 프로젝트를 생성할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 생성 권한이 있으면 true, 없으면 false
     */
}