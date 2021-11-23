package com.nilbrains.nilamdinserver.api.auth;

import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import com.nilbrains.nilamdinserver.entity.req.ChangeUserPass;
import com.nilbrains.nilamdinserver.entity.req.SearchData;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUserApi {

    @Autowired
    private AdminUserService adminUserService;

//    用户登录 返回 token
    @PostMapping("/auth/admin/user/login")
    public ResponseResult loginUser(@RequestBody AdminUser adminUser) {
        return adminUserService.loginUser(adminUser);
    }

//    根据token 返回 用户信息

    @GetMapping("/auth/admin/user/info")
    public ResponseResult userinfo() {
        return adminUserService.userinfo();
    }

//    创建用户

    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/user")
    public ResponseResult createUser(@RequestBody AdminUser adminUser) {
        return this.adminUserService.createUser(adminUser);
    }

//    获取 用户列表
    @GetMapping("/auth/admin/users")
    public ResponseResult users(SearchData searchData) {
        return this.adminUserService.users(searchData);
    }

//    修改 用户信息

    @PreAuthorize("@permission.login()")
    @PutMapping("/auth/admin/user")
    public ResponseResult changeUser(@RequestBody AdminUser adminUser) {
        return this.adminUserService.changeUser(adminUser);
    }

//    禁用 | 启用 用户
    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/user/status")
    public ResponseResult changeUserStatus(@RequestBody AdminUser adminUser) {
        return this.adminUserService.changeUserStatus(adminUser);
    }

    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/user/password")
    public ResponseResult changeUserPass(@RequestBody ChangeUserPass changeUserPass) {
        return this.adminUserService.changeUserPass(changeUserPass);
    }

    @PreAuthorize("@permission.login()")
    @PostMapping("/auth/admin/user/repass")
    public ResponseResult reUserPass(@RequestBody ChangeUserPass adminUser) {
        return this.adminUserService.reUserPass(adminUser);
    }
}
