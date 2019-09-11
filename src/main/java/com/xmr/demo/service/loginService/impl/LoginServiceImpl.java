package com.xmr.demo.service.loginService.impl;

import com.alibaba.fastjson.JSONObject;
import com.xmr.demo.dao.UserMapper;
import com.xmr.demo.domain.User;
import com.xmr.demo.service.BaseService;
import com.xmr.demo.service.loginService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(HttpServletRequest request, HttpServletResponse response, User user) {
        User userData = userMapper.findByUserName(user.getUserName());
        if(!isEmpty(userData)){
            if(userData.getPassword().equals(user.getPassword())){
                String data = userData.getId()+"";
                request.getSession().setAttribute("user",userData);
                String token = base64Encode(data);
                redisUntil.set(token,JSONObject.toJSONString(userData),1800);
                Cookie cookie = new Cookie("loginToken",token);
                cookie.setMaxAge(1800);
                response.addCookie(cookie);
                return doSuccessMsg();
            }else{
                return doErr("密码错误，请输入正确密码");
            }
        }
        return doErr("账号错误，请输入正确账号");
    }
}