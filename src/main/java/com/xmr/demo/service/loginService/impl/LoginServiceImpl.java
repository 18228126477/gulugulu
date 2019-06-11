package com.xmr.demo.service.loginService.impl;

import com.xmr.demo.domain.User;
import com.xmr.demo.service.loginService.LoginService;
import com.xmr.demo.untils.redis.RedisUntilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUntilImpl redisUntilImpl;

    @Override
    public void login(HttpServletResponse response, User user) {
        String token = UUID.randomUUID().toString().replace("-","");
        redisUntilImpl.set(token,user.getUserName(),1800L);
        Cookie cookie = new Cookie("domeLogin",user.getUserName());
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
    }
}
