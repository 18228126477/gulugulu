package com.xmr.demo.controller;

import com.xmr.demo.domain.Character;
import com.xmr.demo.service.domainService.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class Index {

    @Autowired
    private CharacterService characterService;

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
