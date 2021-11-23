package com.nilbrains.nilamdinserver.api.auth;

import com.nilbrains.nilamdinserver.entity.pojo.AdminMenu;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminMenuApi {

    @Autowired
    private AdminMenuService adminMenuService;


//    菜单列表 全部
    @GetMapping("/auth/admin/menus")
    public ResponseResult menus(){
        return  adminMenuService.menus();
    }

//    根据角色 查询 菜单 列表
    @GetMapping("/auth/admin/role/menus")
    public ResponseResult menusForRole(String role){
        return adminMenuService.menusForRole(role);
    }

//    修改菜单
    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/menu/{id}")
    public ResponseResult addMenu(@PathVariable("id") String id, @RequestBody AdminMenu adminMenu){
        return adminMenuService.changeMenu(id,adminMenu);
    }
//    添加菜单

    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/menu")
    public ResponseResult addMenu(@RequestBody AdminMenu adminMenu){
        return adminMenuService.addMenu(adminMenu);
    }

//    删除菜单

    @PreAuthorize("@permission.login()")
    @DeleteMapping("/auth/admin/menu/{id}")
    public ResponseResult deleteMenu(@PathVariable("id") String id) {
        return adminMenuService.deleteMenuById(id);
    }

}
