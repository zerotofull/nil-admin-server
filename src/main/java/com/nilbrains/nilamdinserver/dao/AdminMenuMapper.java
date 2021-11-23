package com.nilbrains.nilamdinserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminMenu;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {


    @Select("select m.* from admin_menu m,admin_role_menu r where m.id = r.menu_id and r.role_id = #{role} order by m.sort desc")
    List<AdminMenu> menusForRole(String role);


}
