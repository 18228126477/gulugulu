package com.xmr.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class Index {

    @RequestMapping("index")
    public String index(){ return "index"; }

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
}
