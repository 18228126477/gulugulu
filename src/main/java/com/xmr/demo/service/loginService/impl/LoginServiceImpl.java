package com.xmr.demo.service.loginService.impl;

import com.alibaba.fastjson.JSONArray;
import com.xmr.demo.dao.UserMapper;
import com.xmr.demo.domain.PageExpire;
import com.xmr.demo.domain.User;
import com.xmr.demo.service.BaseService;
import com.xmr.demo.service.loginService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(HttpServletRequest request, HttpServletResponse response, User user, Integer pageId) {
        User userData = userMapper.findByUserName(user.getUsername());
        PageExpire pageExpire = (PageExpire)request.getSession().getAttribute("pageExpire");
        if(!pageId.equals(pageExpire.getPageId())){
            return doErr("页面已过期请刷新页面重试");
        }
        if(checkTime(pageExpire.getExpireDate())){
            return doErr("页面已过期请刷新页面重试");
        }else{
            if(!isEmpty(userData)){
                if(userData.getPassword().equals(user.getPassword())){
                    request.getSession().removeAttribute("pageExpire");
                    request.getSession().setAttribute(USER,userData);
                    String key = redisUntil.setLogin(UUID.randomUUID().toString(), JSONArray.toJSONString(userData));
                    Cookie cookie = new Cookie(TOKEN,key);
                    cookie.setMaxAge(3600);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return doSuccess();
                }else{
                    return doErr("密码错误，请输入正确密码");
                }
            }
            return doErr("账号错误，请输入正确账号");
        }

    }
}