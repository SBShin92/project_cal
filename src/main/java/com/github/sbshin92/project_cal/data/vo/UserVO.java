package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean canCreateProject;  // 프로젝트 생성 권한을 나타내는 필드 추가

    /**
     * 사용자가 프로젝트를 생성할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 생성 권한이 있으면 true, 없으면 false
     */
    public boolean isCanCreateProject() {
        return canCreateProject || "admin".equals(userAuthority);
    }
}