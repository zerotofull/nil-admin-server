package com.nilbrains.nilamdinserver.service;

import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import com.nilbrains.nilamdinserver.utils.ClaimsUtils;
import com.nilbrains.nilamdinserver.utils.Constants;
import com.nilbrains.nilamdinserver.utils.JwtUtils;
import com.nilbrains.nilamdinserver.utils.TextUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 这是一个 service 基类
 */
public class BaseService{

    /**
     * 检查用户是否 登录
     * @return AdminUser 当前登录的用户
     */
    public AdminUser checkUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(Constants.Setting.HEADER_AUTH);
        if (TextUtil.isEmpty(token)) {
            return null;
        }
        try {
            Claims claims = JwtUtils.parseJWT(token);
            return ClaimsUtils.claims2User(claims);
        } catch (Exception e) {
            return null;
        }
    }

}
