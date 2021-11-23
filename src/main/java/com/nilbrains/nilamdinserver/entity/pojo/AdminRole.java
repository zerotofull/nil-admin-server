package com.nilbrains.nilamdinserver.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminRole {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String roleName;

    private String roleKey;

}
