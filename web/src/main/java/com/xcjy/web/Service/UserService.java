package com.xcjy.web.Service;

import com.xcjy.web.bean.User;
import com.xcjy.web.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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

    public User getByUsernameOrPhone(String username, String phone) {
        User user = null;
        if(StringUtils.isNotBlank(username)) {
            user = userMapper.getByUsername(username);
        }
        if(null == user && StringUtils.isNotBlank(phone)) {
            user = userMapper.getByPhone(phone);
        }
        return user;
    }

    public void insert(User user) {
        userMapper.insertSelective(user);
    }

    public void updateLoginMessage(String currentUserName, Date loginTime, String loginIp) {
        userMapper.updateLoginMessage(currentUserName, loginTime, loginIp, new Date());
    }
}
