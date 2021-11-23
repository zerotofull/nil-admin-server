package com.nilbrains.nilamdinserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nilbrains.nilamdinserver.dao.AdminRoleMapper;
import com.nilbrains.nilamdinserver.dao.AdminRoleMenuMapper;
import com.nilbrains.nilamdinserver.dao.AdminUserMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRole;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRoleMenu;
import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import com.nilbrains.nilamdinserver.entity.req.RoleHaveMenus;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.utils.Constants;
import com.nilbrains.nilamdinserver.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdminRoleService extends BaseService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    public ResponseResult createRole(RoleHaveMenus adminRole) {

        if (TextUtil.isEmpty(adminRole.getRoleKey())) {
            return ResponseResult.FAILED("角色Key不能为空");
        }
        if (TextUtil.isEmpty(adminRole.getRoleName())) {
            return ResponseResult.FAILED("角色名称不能为空");
        }

        AdminRole selectOne = adminRoleMapper.selectOne(new QueryWrapper<AdminRole>().eq("role_key", adminRole.getRoleKey()));

        if (selectOne != null) {
            return ResponseResult.FAILED("角色key已经存在");
        }

        AdminRole role = new AdminRole();
        role.setRoleKey(adminRole.getRoleKey());
        role.setRoleName(adminRole.getRoleName());

        adminRoleMapper.insert(role);

        List<String> menus = adminRole.getMenus();

        List<AdminRoleMenu> roleMenus = new ArrayList<>();

        if (!menus.isEmpty()) {

            for (String menu : menus) {
                AdminRoleMenu roleMenu = new AdminRoleMenu();
                roleMenu.setRoleId(role.getId());
                roleMenu.setMenuId(menu);
                roleMenus.add(roleMenu);
            }

        }

        int insertAll = adminRoleMenuMapper.insertAll(roleMenus);

        if (insertAll > 0) {
            return ResponseResult.SUCCESS("创建成功");
        }

        return ResponseResult.FAILED("创建失败");
    }

    public ResponseResult roles() {
        List<AdminRole> adminRoles = adminRoleMapper.selectList(null);
        return ResponseResult.SUCCESS("获取成功").setData(adminRoles);
    }

    //    修改角色
    @Transactional
    public ResponseResult changeRole(RoleHaveMenus roleHaveMenus) {

        AdminRole adminRole = new AdminRole();
        adminRole.setId(roleHaveMenus.getRoleId());
        adminRole.setRoleName(roleHaveMenus.getRoleName());

        int update = adminRoleMapper.updateById(adminRole);

        if (!(update > 0)) {
            return ResponseResult.FAILED("修改失败");
        }

        List<String> menus = roleHaveMenus.getMenus();

        List<AdminRoleMenu> roleMenus = new ArrayList<>();

        int flag = adminRoleMenuMapper.delete(new QueryWrapper<AdminRoleMenu>().eq("role_id", roleHaveMenus.getRoleId()));

        if (!(flag > 0)) {
            return ResponseResult.FAILED("修改失败");
        }

        if (!menus.isEmpty()) {

            for (String menu : menus) {
                AdminRoleMenu roleMenu = new AdminRoleMenu();
                roleMenu.setRoleId(roleHaveMenus.getRoleId());
                roleMenu.setMenuId(menu);
                roleMenus.add(roleMenu);
            }

        }

        int insertAll = adminRoleMenuMapper.insertAll(roleMenus);

        if (insertAll > 0) {
            return ResponseResult.SUCCESS("修改成功");
        }

        return ResponseResult.FAILED("修改失败");
    }

    @Transactional
    public ResponseResult deleteRole(String id) {

        Integer count = adminUserMapper.selectCount(new QueryWrapper<AdminUser>().eq("role_id", id));

        if (count>0) {
            return ResponseResult.FAILED("该角色使用中");
        }


//        删除角色
        adminRoleMapper.deleteById(id);

        int flag = adminRoleMenuMapper.delete(new QueryWrapper<AdminRoleMenu>().eq("role_id", id));

        if (flag > 0) {
            return ResponseResult.SUCCESS("删除成功");
        }

        return ResponseResult.FAILED("删除失败");
    }
}
