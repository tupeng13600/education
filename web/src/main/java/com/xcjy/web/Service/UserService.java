package com.xcjy.web.Service;

import com.xcjy.upc.util.UpcSecurityUtil;
import com.xcjy.web.bean.User;
import com.xcjy.web.controller.req.RegisterReq;
import com.xcjy.web.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by tupeng on 2017/7/18.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    public User getByUsernameOrPhone(String username, String phone) {
        User user = null;
        if (StringUtils.isNotBlank(username)) {
            user = userMapper.getByUsername(username);
        }
        if (null == user && StringUtils.isNotBlank(phone)) {
            user = userMapper.getByPhone(phone);
        }
        return user;
    }

    public void insert(RegisterReq req) {
        User regUser = new User();
        BeanUtils.copyProperties(req, regUser);
        regUser.setLastLoginIp("0.0.0.0");
        regUser.setLastLoginTime(new Date());
        regUser.setSalt(UpcSecurityUtil.randomString());
        regUser.setPassword(UpcSecurityUtil.encryptPwd(req.getPassword(), new SimpleByteSource(regUser.getSalt())));
        regUser.setRoleId("1");
        userMapper.insert(regUser);
    }

    public void updateLoginMessage(String currentUserName, Date loginTime, String loginIp) {
        userMapper.updateLoginMessage(currentUserName, loginTime, loginIp, new Date());
    }
}
