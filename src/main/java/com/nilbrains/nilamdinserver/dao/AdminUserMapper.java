package com.nilbrains.nilamdinserver.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import com.nilbrains.nilamdinserver.entity.resp.AdminUserResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    @Select("SELECT * FROM `admin_user` ${ew.customSqlSegment}")
    IPage<AdminUserResp> page(IPage<AdminUser> page, @Param("ew") QueryWrapper<AdminUser> ew);

}
