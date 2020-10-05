package com.xmr.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.xmr.demo.common","com.xmr.demo.config","com.xmr.demo.controller","com.xmr.demo.dao"
        ,"com.xmr.demo.domain","com.xmr.demo.param","com.xmr.demo.service","com.xmr.demo.untils"})
@MapperScan("com.xmr.demo.dao")
@ServletComponentScan
public class DemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}