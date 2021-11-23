package com.nilbrains.nilamdinserver.entity.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminUserResp {

    private String id;

    private String name;

    private String username;

    private String roleId;

    private String status;
}
