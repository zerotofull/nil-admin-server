package com.nilbrains.nilamdinserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nilbrains.nilamdinserver.dao.AdminMenuMapper;
import com.nilbrains.nilamdinserver.dao.AdminRoleMenuMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminMenu;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRoleMenu;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AdminMenuService extends BaseService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    public ResponseResult menus() {
        List<AdminMenu> adminMenus = adminMenuMapper.selectList(new QueryWrapper<>());
        return ResponseResult.SUCCESS("获取成功").setData(adminMenus);
    }

    public ResponseResult menusForRole(String role) {
        if (TextUtil.isEmpty(role)) {
            return ResponseResult.FAILED("角色不能为空");
        }
        List<AdminMenu> adminMenus = adminMenuMapper.menusForRole(role);
        return ResponseResult.SUCCESS("获取成功").setData(adminMenus);
    }


    public ResponseResult addMenu(AdminMenu adminMenu) {

        if (TextUtil.isEmpty(adminMenu.getName())) {
            return ResponseResult.FAILED("名称不能为空");
        }

        if (TextUtil.isEmpty(adminMenu.getPath())) {
            return ResponseResult.FAILED("路径不能为空");
        }

        if (TextUtil.isEmpty(adminMenu.getPid())) {
            return ResponseResult.FAILED("父级目录不能为空");
        }

        int insert = adminMenuMapper.insert(adminMenu);

        if (insert>0) {
            return ResponseResult.SUCCESS("创建成功");
        }

        return ResponseResult.FAILED("创建失败");
    }

    public ResponseResult changeMenu(String id, AdminMenu adminMenu) {

        if (TextUtil.isEmpty(adminMenu.getId())) {
            return ResponseResult.FAILED("Id不能为空");
        }

        if (!id.equals(adminMenu.getId())){
            return ResponseResult.FAILED("修改内容id不一致");
        }


        int update = adminMenuMapper.updateById(adminMenu);

        if (update > 0) {
            return ResponseResult.SUCCESS("修改成功");
        }

        return ResponseResult.FAILED("修改失败");
    }

//    删除 菜单
    @Transactional
    public ResponseResult deleteMenuById(String id) {
//        断定 menu id 存在

        if ("0".equals(id)) {
            return ResponseResult.FAILED("id不能为零");
        }

//        判断 是否有 角色使用

        Integer count = adminRoleMenuMapper.selectCount(new QueryWrapper<AdminRoleMenu>().eq("menu_id", id));

        if (count > 0) {
            return ResponseResult.FAILED("当前菜单被使用中");
        }

//        一并删除子菜单

        int delete = adminMenuMapper.delete(new QueryWrapper<AdminMenu>().eq("id", id).or().eq("pid", id));

        if (delete > 0) {
            return ResponseResult.SUCCESS("删除成功");
        }

        return ResponseResult.FAILED("删除失败");
    }
}
