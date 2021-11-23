package com.nilbrains.nilamdinserver.api.auth;

import com.nilbrains.nilamdinserver.entity.pojo.AdminRole;
import com.nilbrains.nilamdinserver.entity.req.RoleHaveMenus;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminRoleAPi {

    @Autowired
    private AdminRoleService adminRoleService;

//    获取角色列表
    @GetMapping("/auth/admin/roles")
    public ResponseResult roles() {
        return adminRoleService.roles();
    }

//    添加角色

    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/role")
    public ResponseResult createRole(@RequestBody RoleHaveMenus adminRole) {
        return adminRoleService.createRole(adminRole);
    }

//    修改角色 拥有菜单

    @PreAuthorize("@permission.login()")
    @PutMapping("/auth/admin/role")
    public ResponseResult changeRole(@RequestBody RoleHaveMenus roleHaveMenus) {
        return adminRoleService.changeRole(roleHaveMenus);
    }

//    删除角色


    @PreAuthorize("@permission.login()")
    @DeleteMapping("/auth/admin/role/{id}")
    public ResponseResult deleteRole(@PathVariable("id") String id) {
        return adminRoleService.deleteRole(id);
    }

}
