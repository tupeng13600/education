package com.xcjy.web.Service;

import com.xcjy.upc.model.UpcLoginSuccessModel;
import com.xcjy.upc.model.UpcUser;
import com.xcjy.upc.service.AuthMessageService;
import com.xcjy.upc.util.UserUtil;
import com.xcjy.web.bean.Role;
import com.xcjy.web.bean.User;
import com.xcjy.web.mapper.RoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tupeng on 2017/7/18.
 */
public class AuthService implements AuthMessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UpcUser getUser(String username) {
        User user = userService.getByUsernameOrPhone(username, username);
        return null == user ? null : new UpcUser(username, user.getPassword(), user.getSalt());
    }

    @Override
    public Set<String> getRole(String username) {
        User user = userService.getByUsernameOrPhone(username, username);
        if(null != user && StringUtils.isNotBlank(user.getRoleId())) {
            Set<Role> roleSet = roleService.getRoleByIds(Arrays.asList(user.getRoleId().split(",")));
            if(CollectionUtils.isNotEmpty(roleSet)) {
                return roleSet.stream().map(Role::getName).collect(Collectors.toSet());
            }
        }
        return new HashSet<>();
    }

    @Override
    public void saveUserMessage(UpcLoginSuccessModel model) {
        userService.updateLoginMessage(UserUtil.getCurrentUserName(), model.getLoginTime(), model.getLoginIp());
    }
}
