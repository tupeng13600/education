package com.xcjy.web.controller;

import com.xcjy.upc.util.UpcSecurityUtil;
import com.xcjy.web.Service.UserService;
import com.xcjy.web.bean.User;
import com.xcjy.web.common.exception.EducationException;
import com.xcjy.web.controller.req.LoginReq;
import com.xcjy.web.controller.req.RegisterReq;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by 22670 on 2017/7/18.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册新的用户
     *
     * @param req
     */
    @PostMapping
    public void register(@RequestBody RegisterReq req) {
        User user = userService.getByUsernameOrPhone(req.getUsername(), req.getPhone());
        if (null != user) {
            throw new EducationException("用户名或手机号码已经被占用");
        }
        User regUser = new User();
        BeanUtils.copyProperties(req, regUser);
        regUser.setId(UpcSecurityUtil.randomString());
        regUser.setCreateTime(new Date());
        regUser.setUpdateTime(new Date());
        regUser.setLastLoginIp("0.0.0.0");
        regUser.setLastLoginTime(new Date());
        regUser.setDeleted(false);
        regUser.setSalt(UpcSecurityUtil.randomString());
        regUser.setPassword(UpcSecurityUtil.encryptPwd(req.getPassword(), new SimpleByteSource(regUser.getSalt())));
        regUser.setRoleId("1");
        userService.insert(regUser);
    }

}
