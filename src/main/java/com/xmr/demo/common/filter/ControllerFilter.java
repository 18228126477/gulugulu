package com.xmr.demo.common.filter;


import com.xmr.demo.common.AbnormalEnum;
import com.xmr.demo.dao.UserMapper;
import com.xmr.demo.domain.User;
import com.xmr.demo.untils.redis.RedisUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="controllerFilter",urlPatterns={"/*"})
public class ControllerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ControllerFilter.class);

    @Autowired
    public RedisUntil redisUntil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info(AbnormalEnum.EIGHT.getDesc());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info(request.getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
