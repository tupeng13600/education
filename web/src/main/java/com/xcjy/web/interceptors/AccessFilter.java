package com.xcjy.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tupeng on 2017/7/26.
 */
public class AccessFilter implements Filter {

    private final static Logger SERVER_LOG = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String origin = httpRequest.getHeader("Origin");
        if (origin == null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        } else {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
        }

        String method = httpRequest.getMethod();
        SERVER_LOG.info("request method:{}", method);

        httpResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,PATCH,DELETE,OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,__skcy, No-Cache, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires,  X-E4M-With");
        httpResponse.setHeader("Access-Control-Max-Age", "1728000");

        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
