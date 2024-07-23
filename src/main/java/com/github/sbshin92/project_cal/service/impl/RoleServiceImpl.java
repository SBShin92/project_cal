package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	        if (role != null) {
	            roleDAO.insert(role);
	        } else {
	            throw new IllegalArgumentException("Role cannot be null");
	        }
	    }

	@Override
	public void updateRole(RoleVO role) {
		roleDAO.update(role);
	}

	@Override
	public void deleteRole(int roleId) {
		roleDAO.delete(roleId);
	}
	  @Override
	    public RoleVO getRoleByUserId(int userId) {
	        return roleDAO.findByUserId(userId);
	    }

	   @Override
	    @Transactional
	    public void createOrUpdateRole(RoleVO role) {
	        try {
	            if (role == null || role.getUserId() <= 0) {
	                throw new IllegalArgumentException("Invalid role data");
	            }

	            RoleVO existingRole = roleDAO.findByUserId(role.getUserId());
	            if (existingRole == null) {
	                roleDAO.insert(role);
	            } else {
	                role.setRoleId(existingRole.getRoleId()); // Ensure we're updating the correct role
	                roleDAO.updateByUserId(role);
	            }
	        } catch (DataAccessException e) {
	            throw new RuntimeException("Failed to create or update role", e);
	        } catch (Exception e) {
	            throw new RuntimeException("An unexpected error occurred", e);
	        }
	    }
	

}
