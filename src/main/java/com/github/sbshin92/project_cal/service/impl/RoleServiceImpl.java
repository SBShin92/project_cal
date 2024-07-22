package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.RoleDAO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public List<RoleVO> getAllRoles() {
		return roleDAO.findAll();
	}

	@Override
	public RoleVO getRoleById(int roleId) {
		return roleDAO.findById(roleId);
	}

	@Override
	public void createRole(RoleVO role) {
		roleDAO.insert(role);
	}

	@Override
	public void updateRole(RoleVO role) {
		roleDAO.update(role);
	}

	@Override
	public void deleteRole(int roleId) {
		roleDAO.delete(roleId);
	}
	
	

}
