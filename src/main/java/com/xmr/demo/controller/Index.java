package com.xmr.demo.controller;

import com.xmr.demo.untils.redis.RedisUntilImpl;
import com.xmr.demo.untils.redis.impl.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("index")
public class Index extends BaseController{

    @RequestMapping("index")
    public String index(){
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
