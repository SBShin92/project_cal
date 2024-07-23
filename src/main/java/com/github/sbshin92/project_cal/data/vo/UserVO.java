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
    private boolean canCreateProject;  // 프로젝트 생성 권한을 나타내는 필드 추가

    private RoleVO role;  // RoleVO와의 관계를 나타내는 필드
    /**
     * 사용자가 프로젝트를 생성할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 생성 권한이 있으면 true, 없으면 false
     */
//    public boolean isCanCreateProject() {
//        return canCreateProject || "admin".equals(userAuthority);
//    }


    /**
     * 사용자가 프로젝트를 생성할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 생성 권한이 있으면 true, 없으면 false
     */
    public boolean isCanCreateProject() {
        return canCreateProject || "admin".equals(userAuthority) || (role != null && role.getProjectCreate());
    }

    /**
     * 사용자가 프로젝트를 읽을 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 읽기 권한이 있으면 true, 없으면 false
     */
    public boolean isCanReadProject() {
        return "admin".equals(userAuthority) || (role != null && role.getProjectRead());
    }

    /**
     * 사용자가 프로젝트를 수정할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 수정 권한이 있으면 true, 없으면 false
     */
    public boolean isCanUpdateProject() {
        return "admin".equals(userAuthority) || (role != null && role.getProjectUpdate());
    }

    /**
     * 사용자가 프로젝트를 삭제할 수 있는 권한이 있는지 확인합니다.
     * @return 프로젝트 삭제 권한이 있으면 true, 없으면 false
     */
    public boolean isCanDeleteProject() {
        return "admin".equals(userAuthority) || (role != null && role.getProjectDelete());
    }

    /**
     * 사용자의 역할을 설정합니다.
     */
    public void setRole(RoleVO role) {
        this.role = role;
    }

    //사용자의 역할을 반환합니다.
    public RoleVO getRole() {
        return role;
    }

 



}