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
@Builder
@Data
public class RoleVO {
    private int roleId;
    private String roleName;
    private Boolean projectCreate;
    private Boolean projectRead;
    private Boolean projectUpdate;
    private Boolean projectDelete;
    private Boolean taskCreate;
    private Boolean taskRead;
    private Boolean taskUpdate;
    private Boolean taskDelete;
	

}
