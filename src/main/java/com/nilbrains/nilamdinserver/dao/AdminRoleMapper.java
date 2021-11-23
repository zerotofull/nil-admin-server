package com.nilbrains.nilamdinserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminRole;
import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
}
