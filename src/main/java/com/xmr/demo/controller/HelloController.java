package com.xmr.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;
    @PostMapping("set")
    public String set(HttpSession session){
        session.setAttribute("user","javaweb");
        return String.valueOf(port);
    }

    @PostMapping("get")
    public String get(HttpSession session){
        return session.getAttribute("user")+""+port;
    }
}
