package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.service.RoleService;

@RequestMapping("/manager/roles")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;


    // 모든 역할 목록을 조회하고 users 페이지로 이동
    @GetMapping({"", "/", "/list"})
    public String listRoles(Model model) {
        model.addAttribute("roleVOs", roleService.getAllRoles());
        return "manager/manager-roles";
    }

//     특정 역할의 상세 정보를 조회하고 view 페이지로 이동
//    @GetMapping("/{roleId}")
//    public String viewRole(@PathVariable int roleId, Model model) {
//        model.addAttribute("role", roleService.getRoleById(roleId));
//        return "manager/view";
//    }
//
    // 새로운 역할 생성 폼을 표시
    @GetMapping("/create")
    public String createRoleForm(Model model) {
        return "manager/manager-roles-create";
    }
//
//    // 새로운 역할을 생성하고 역할 목록 페이지로 리다이렉트
    @PostMapping("/create")
    public String createRole(@ModelAttribute RoleVO role) {
        roleService.createRole(role);
        return "redirect:/manager/roles";
    }
//
//    // 기존 역할 수정 폼을 표시
//    @GetMapping("/edit/{roleId}")
//    public String editRoleForm(@PathVariable int roleId, Model model) {
//        model.addAttribute("role", roleService.getRoleById(roleId));
//        return "manager/users/form";
//    }
//
//    // 기존 역할을 수정하고 역할 목록 페이지로 리다이렉트
//    @PostMapping("/edit/{roleId}")
//    public String editRole(@PathVariable int roleId, @ModelAttribute RoleVO role) {
//        role.setRoleId(roleId);
//        roleService.updateRole(role);
//        return "redirect:/manager/users";
//    }
//
//    // 특정 역할을 삭제하고 역할 목록 페이지로 리다이렉트
//    @PostMapping("/delete/{roleId}")
//    public String deleteRole(@PathVariable int roleId) {
//        roleService.deleteRole(roleId);
//        return "redirect:/manager/users";
//    }
}