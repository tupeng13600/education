package com.xcjy.web.interceptors;

import com.xcjy.upc.util.UserUtil;
import com.xcjy.web.Service.UserService;
import com.xcjy.web.bean.User;
import com.xcjy.web.common.XcjyThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tupeng on 2017/7/22.
 */
public class BaseMessageInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 将schoolId写进ThreadLocal
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String username = UserUtil.getCurrentUserName();
        if (StringUtils.isNotBlank(username)) {
            User user = userService.getByUsernameOrPhone(username, username);
            if (null != user) {
                XcjyThreadLocal.setSchoolId(user.getSchoolId());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
