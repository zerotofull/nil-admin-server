package com.nilbrains.nilamdinserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminMenu;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRoleMenu;
import com.nilbrains.nilamdinserver.entity.req.RoleHaveMenus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenu> {

    @Insert("<script>" +
            "INSERT INTO admin_role_menu (id, role_id, menu_id) VALUES " +
            "    <foreach collection=\"list\" separator=\",\" item=\"item\">" +
            "        (#{item.id},#{item.roleId},#{item.menuId}) " +
            "    </foreach>" +
            "</script>")
    int insertAll(List<AdminRoleMenu> roleMenus);
}
