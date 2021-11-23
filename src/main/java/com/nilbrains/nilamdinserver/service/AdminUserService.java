package com.nilbrains.nilamdinserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nilbrains.nilamdinserver.dao.AdminUserMapper;
import com.nilbrains.nilamdinserver.entity.pojo.AdminUser;
import com.nilbrains.nilamdinserver.entity.req.ChangeUserPass;
import com.nilbrains.nilamdinserver.entity.req.SearchData;
import com.nilbrains.nilamdinserver.entity.resp.AdminUserResp;
import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import com.nilbrains.nilamdinserver.utils.ClaimsUtils;
import com.nilbrains.nilamdinserver.utils.Constants;
import com.nilbrains.nilamdinserver.utils.JwtUtils;
import com.nilbrains.nilamdinserver.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
public class AdminUserService extends BaseService {

    @Autowired
    private AdminUserMapper adminUserMapper;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseResult createUser(AdminUser adminUser) {
        log.info("createUser -- >  user == > " + adminUser);

        if (TextUtil.isEmpty(adminUser.getUsername())) {
            return ResponseResult.FAILED("账号不能为空");
        }

        if (TextUtil.isEmpty(adminUser.getPassword())) {
            return ResponseResult.FAILED("密码不能为空");
        }

        if (TextUtil.isEmpty(adminUser.getName())) {
            return ResponseResult.FAILED("昵称不能为空");
        }

        if (TextUtil.isEmpty(adminUser.getRoleId())) {
            return ResponseResult.FAILED("角色不能为空");
        }

//        判断账号是否存在
        QueryWrapper<AdminUser> qw = new QueryWrapper<>();
        qw.eq("username", adminUser.getUsername());
        AdminUser selectOne = adminUserMapper.selectOne(qw);
        if (selectOne != null) {
            return ResponseResult.FAILED("该账号已注册");
        }

        AdminUser dbUser = new AdminUser();
        dbUser.setUsername(adminUser.getUsername());
        dbUser.setName(adminUser.getName());
        dbUser.setRoleId(adminUser.getRoleId());
        dbUser.setPassword(bCryptPasswordEncoder.encode(adminUser.getPassword()));
        dbUser.setStatus(Constants.DB.FLAG_TRUE);

        int insert = adminUserMapper.insert(dbUser);

        if (insert > 0) {
            return ResponseResult.SUCCESS("注册成功");
        }
        return ResponseResult.FAILED("注册失败");
    }

    public ResponseResult loginUser(AdminUser adminUser) {
        if (TextUtil.isEmpty(adminUser.getUsername())) {
            return ResponseResult.FAILED("账号不能为空");
        }

        if (TextUtil.isEmpty(adminUser.getPassword())) {
            return ResponseResult.FAILED("密码不能为空");
        }
        AdminUser authUserDb = adminUserMapper.selectOne(new QueryWrapper<AdminUser>().eq("username", adminUser.getUsername()));

        log.info("authUserDb === > " + authUserDb);

        if (authUserDb == null) {
            return ResponseResult.FAILED("账号或密码不正确");
        }

        boolean matches = bCryptPasswordEncoder.matches(adminUser.getPassword(), authUserDb.getPassword());
        if (!matches) {
            return ResponseResult.FAILED("账号或密码不正确");
        }

        if (Constants.DB.FLAG_FALSE.equals(authUserDb.getStatus())) {
            return ResponseResult.FAILED("该账号已禁用");
        }

        Map<String, Object> claims = ClaimsUtils.user2Claims(authUserDb);

        String token = JwtUtils.createToken(claims);
        return ResponseResult.SUCCESS("登录成功").setData(token);
    }

    public ResponseResult userinfo() {
        AdminUser userByCheck = checkUser();
        AdminUser user = adminUserMapper.selectOne(new QueryWrapper<AdminUser>().eq("id", userByCheck.getId()));
        if (user == null) {
            return ResponseResult.FAILED("不存在");
        }
        user.setPassword(Constants.DB.NULL);
        return ResponseResult.SUCCESS().setData(user);
    }

    public ResponseResult users(SearchData searchData) {
        IPage<AdminUser> page = new Page<>(searchData.getCurrent(), searchData.getSize());

        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.like("name",searchData.getKey()).or().like("username",searchData.getKey());
        IPage<AdminUserResp> adminUserRespIPage = adminUserMapper.page(page, wrapper);

        return ResponseResult.SUCCESS("获取成功").setData(adminUserRespIPage);
    }

    public ResponseResult changeUser(AdminUser adminUser) {

        log.info("changeUser -- > adminUser == >" + adminUser);

//        判断账号是否存在
        QueryWrapper<AdminUser> qw = new QueryWrapper<>();
        qw.eq("username", adminUser.getUsername());
        qw.ne("id", adminUser.getId());
        AdminUser selectOne = adminUserMapper.selectOne(qw);
        if (selectOne != null) {
            return ResponseResult.FAILED("该账号已注册");
        }

        adminUser.setPassword(null);

        int update = adminUserMapper.updateById(adminUser);

        if (update > 0) {
            return ResponseResult.SUCCESS("修改成功");
        }

        return ResponseResult.FAILED("修改失败");
    }

    public ResponseResult changeUserStatus(AdminUser adminUser) {

        String RetText = Constants.DB.FLAG_TRUE.equals(adminUser.getStatus()) ? "禁用" : "启用";

        if (TextUtil.isEmpty(adminUser.getId())) {
            return ResponseResult.FAILED("用户id为空");
        }

        if (Constants.DB.FLAG_TRUE.equals(adminUser.getStatus())) {
            adminUser.setStatus(Constants.DB.FLAG_FALSE);
        } else {
            adminUser.setStatus(Constants.DB.FLAG_TRUE);
        }


        int update = adminUserMapper.updateById(adminUser);

        if (update > 0) {
            return ResponseResult.SUCCESS(RetText + "成功");
        }

        return ResponseResult.FAILED(RetText + "失败");
    }

    @Transactional
    public ResponseResult changeUserPass(ChangeUserPass changeUserPass) {

        AdminUser adminUser = checkUser();
        if (!adminUser.getId().equals(changeUserPass.getUid())) {
            return ResponseResult.FAILED("用户不一致");
        }

        AdminUser adminDb = adminUserMapper.selectById(adminUser.getId());

        boolean matches = bCryptPasswordEncoder.matches(changeUserPass.getOldPass(), adminDb.getPassword());
        if (!matches) {
            return ResponseResult.FAILED("当前密码错误");
        }

        if (!changeUserPass.getNewPass().equals(changeUserPass.getRepeatPass())) {
            return ResponseResult.FAILED("两次输入的密码不一致");
        }
        AdminUser user = new AdminUser();
        user.setId(adminDb.getId());
        user.setPassword(bCryptPasswordEncoder.encode(changeUserPass.getRepeatPass()));

        int update = adminUserMapper.updateById(user);

        if (update > 0) {
            return ResponseResult.SUCCESS("修改成功");
        }

        return ResponseResult.FAILED("修改失败");
    }

//    重置密码
    public ResponseResult reUserPass(ChangeUserPass adminUser) {
        log.info("uid === > " + adminUser.getUid());

        if (TextUtil.isEmpty(adminUser.getUid())) {
            return ResponseResult.FAILED("用户id为空");
        }
        AdminUser user = new AdminUser();
        user.setId(adminUser.getUid());
        user.setPassword(bCryptPasswordEncoder.encode(Constants.DB.DEFALT_PASS));

        int update = adminUserMapper.updateById(user);

        if (update > 0) {
            return ResponseResult.SUCCESS("重置成功");
        }

        return ResponseResult.FAILED("重置失败");
    }
}
