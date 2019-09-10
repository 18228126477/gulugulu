package com.xmr.demo.controller;

import com.xmr.demo.domain.Character;
import com.xmr.demo.domain.User;
import com.xmr.demo.service.domainService.CharacterService;
import com.xmr.demo.service.loginService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("indexData")
@ResponseBody
public class IndexData extends BaseController{

    @Autowired
    private CharacterService characterService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("index")
    public List<Character> index(){
        return characterService.findAll();
    }

    @RequestMapping("login")
    public String login(HttpServletResponse response,User user){
        return loginService.login(response, user);
    }
}
