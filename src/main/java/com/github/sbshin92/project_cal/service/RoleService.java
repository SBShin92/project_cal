package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.RoleVO;

public interface RoleService {

	List<RoleVO> getAllRoles();

	RoleVO getRoleById(int roleId);

	void createRole(RoleVO role);

	void updateRole(RoleVO role);

	void deleteRole(int roleId);

	RoleVO getRoleByUserId(int userId);

	void createOrUpdateRole(RoleVO role);

}
