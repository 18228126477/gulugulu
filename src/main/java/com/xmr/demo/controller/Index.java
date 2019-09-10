package com.xmr.demo.controller;
import com.xmr.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class Index extends BaseController{

    @RequestMapping("index")
    public String index(User user){
        System.out.println(user.getUserName());
        return "index";
    }

    @RequestMapping("alipay")
    public String alipay(){
        return "alipay";
    }

    @RequestMapping("webSocket")
    public String webSocket(){
        return "webSocket";
    }

    @RequestMapping("webSocketTest")
    public String webSocketTest(){
        return "webSocketTest";
    }

    @RequestMapping("login")
    public String login(){ return "login"; }
}
