package com.xmr.demo.study.springbeanlifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Benz implements InitializingBean, DisposableBean {

    public Benz(){
        System.out.println("Benz被创建..............");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Benz正在init..............");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Benz已被destroy..............");
    }
}
