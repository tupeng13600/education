package com.xcjy.web.interceptors;

import com.xcjy.upc.util.UserUtil;
import com.xcjy.web.common.SchoolThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tupeng on 2017/7/23.
 */
public class IsLoginInterceptors implements HandlerInterceptor {

    private static final String loginUri = "/upc/login";

    private static Logger logger = LoggerFactory.getLogger(IsLoginInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("User:{},  request uri:{},  schoolId is:{}", UserUtil.getCurrentUserName(), request.getRequestURI(), SchoolThreadLocal.getSchoolId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        String username = UserUtil.getCurrentUserName();
        if (StringUtils.isBlank(username) && null != modelAndView) {
            modelAndView.setViewName("login");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
