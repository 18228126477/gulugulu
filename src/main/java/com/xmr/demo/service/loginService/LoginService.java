package com.xmr.demo.service.loginService;

import com.xmr.demo.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface LoginService {

    String login(HttpServletRequest request, HttpServletResponse response, User user);
}
