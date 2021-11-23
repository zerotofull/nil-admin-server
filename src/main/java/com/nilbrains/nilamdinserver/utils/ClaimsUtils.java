package com.nilbrains.nilamdinserver.utils;

import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON WEB TOKEN
 * 荷载信息
 * 创建 以及 解析
 */
public class ClaimsUtils {

    public static final String ID = "id";
    public static final String ACCOUNT = "username";
    public static final String NAME = "name";
    public static final String ROLE = "role";


    public static Map<String, Object> user2Claims(AdminUser zeroUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, zeroUser.getId());
        claims.put(NAME, zeroUser.getName());
        claims.put(ACCOUNT, zeroUser.getUsername());
        claims.put(ROLE, zeroUser.getRoleId());
        return claims;
    }

    public static AdminUser claims2User(Claims claims) {
        AdminUser zeroUser = new AdminUser();
        String id = (String) claims.get(ID);
        zeroUser.setId(id);
        String account = (String) claims.get(ACCOUNT);
        zeroUser.setUsername(account);
        String name = (String) claims.get(NAME);
        zeroUser.setName(name);
        String role = (String) claims.get(ROLE);
        zeroUser.setRoleId(role);
        return zeroUser;
    }
}
