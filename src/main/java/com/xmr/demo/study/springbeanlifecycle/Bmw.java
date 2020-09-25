package com.xmr.demo.study.springbeanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("bmwA")
public class Bmw {

    public Bmw(){
        System.out.println("Bmw被创建..............");
    }

    @PostConstruct
    public void init(){
        System.out.println("Bmw正在@PostConstruct..............");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bmw已被@PreDestroy..............");
    }
}
