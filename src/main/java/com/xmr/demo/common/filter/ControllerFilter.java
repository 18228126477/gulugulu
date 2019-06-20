package com.xmr.demo.common.filter;


import com.xmr.demo.common.AbnormalEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName="controllerFilter",urlPatterns={"/*"})
public class ControllerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ControllerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info(AbnormalEnum.EIGHT.getDesc());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        /*if(!"/static".equals(requestURI.substring(0,7)))
            logger.info(requestURI);*/
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
