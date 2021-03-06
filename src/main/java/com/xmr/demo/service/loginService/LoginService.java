package com.xmr.demo.service.loginService;

import com.xmr.demo.domain.PageExpire;
import com.xmr.demo.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface LoginService {

    String USER = "user";
    String TOKEN = "loginToken";

    String login(HttpServletRequest request, HttpServletResponse response, User user, Integer pageId);
}
