package com.xmr.demo.controller;

import com.xmr.demo.domain.PageExpire;
import com.xmr.demo.domain.User;
import com.xmr.demo.service.loginService.LoginService;
import com.xmr.demo.untils.until.Cipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    @RequestMapping("index")
    public String login(){
        return "login";
    }

    @RequestMapping("loginId")
    @ResponseBody
    public int loginId(HttpServletRequest request){
        int pageId = Cipher.pageId();
        PageExpire pageExpire = new PageExpire();
        pageExpire.setExpireDate(getNowDate());
        pageExpire.setPageId(pageId);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300);
        session.setAttribute("pageExpire",pageExpire);
        return pageId;
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, User user, Integer pageId){
        return loginService.login(request,response, user,pageId);
    }
}
