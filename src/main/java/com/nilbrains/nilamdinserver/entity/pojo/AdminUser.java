package com.nilbrains.nilamdinserver.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminUser {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String username;

    private String password;

    private String roleId;

    private String status;

    private String token;
}
