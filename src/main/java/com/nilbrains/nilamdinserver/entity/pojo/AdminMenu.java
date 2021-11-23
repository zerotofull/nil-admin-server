package com.nilbrains.nilamdinserver.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminMenu {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String icon;

    private String path;

    private String pid;

    private Integer sort;
}
