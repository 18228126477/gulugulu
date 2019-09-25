package com.xmr.demo.common.interceptor;

import com.xmr.demo.dao.UserMapper;
import com.xmr.demo.domain.User;
import com.xmr.demo.untils.redis.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisUntil redisUntil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        Cookie cookie = getCookie(cookies);
        if(session.getAttribute("user")!=null){
            return true;
        }else if(cookie != null){
            if(redisUntil.hasKey(cookie.getValue())){
                Map login = redisUntil.getLogin(cookie.getValue());
                User user = userMapper.findById(Integer.valueOf(login.get("id").toString()));
                request.getSession().setAttribute("user",user);
                return true;
            }else{
                response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/login/index");
                return false;
            }
        }
        response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/login/index");
        return false;
    }

    private Cookie getCookie(Cookie[] cookies){
        Cookie cookie = null;
        if(cookies ==null){
            return null;
        }
        for(Cookie coo:cookies){
            if("loginToken".equals(coo.getName())){
                cookie = coo;
            }
        }
        return cookie;
    }
}
