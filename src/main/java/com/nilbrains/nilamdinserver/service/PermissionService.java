package com.nilbrains.nilamdinserver.service;


import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import org.springframework.stereotype.Service;

@Service("permission")
public class PermissionService extends BaseService{

    public boolean login() {
        AdminUser user = checkUser();
        return user != null;
    }

}
