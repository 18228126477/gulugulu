package com.xmr.demo.service.loginService;

import com.xmr.demo.domain.User;

import javax.servlet.http.HttpServletResponse;


public interface LoginService {

    void login(HttpServletResponse response, User user);
}
