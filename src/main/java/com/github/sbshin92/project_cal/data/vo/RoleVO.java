package com.github.sbshin92.project_cal.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleVO {
    private int roleId;
    private String roleName;
    private String roleDescription;
    private Boolean projectCreate = false;
    private Boolean projectRead = false;
    private Boolean projectUpdate = false;
    private Boolean projectDelete = false;
    private Boolean taskCreate = false;
    private Boolean taskRead = false;
    private Boolean taskUpdate = false;
    private Boolean taskDelete = false;
	
    // 조인용 필드
    private int roleUsersCount;

}
