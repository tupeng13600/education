package com.xcjy.web.Service;

import com.xcjy.upc.model.UpcLoginSuccessModel;
import com.xcjy.upc.model.UpcUser;
import com.xcjy.upc.service.AuthMessageService;
import com.xcjy.upc.util.UserUtil;
import com.xcjy.web.bean.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 22670 on 2017/7/18.
 */
public class AuthService implements AuthMessageService {

    @Autowired
    private UserService userService;

    @Override
    public UpcUser getUser(String username) {
        User user = userService.getByUsernameOrPhone(username, username);
        return null == user ? null : new UpcUser(username, user.getPassword(), user.getSalt());
    }

    @Override
    public Set<String> getRole(String username) {
        Set<String> set = new HashSet<>();
        set.add("ceshi");
        set.add("ceshih");
        return set;
    }

    @Override
    public void saveUserMessage(UpcLoginSuccessModel model) {
        userService.updateLoginMessage(UserUtil.getCurrentUserName(), model.getLoginTime(), model.getLoginIp());
    }
}
