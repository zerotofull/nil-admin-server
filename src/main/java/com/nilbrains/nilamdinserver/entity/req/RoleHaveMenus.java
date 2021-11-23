package com.nilbrains.nilamdinserver.entity.req;

import lombok.Data;

import java.util.List;

@Data
public class RoleHaveMenus {
    private String roleId;
    private String roleName;
    private String roleKey;
    private List<String> Menus;
}
