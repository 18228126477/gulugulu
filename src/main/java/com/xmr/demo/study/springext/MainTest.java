package com.xmr.demo.study.springext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);
        context.publishEvent(new ApplicationEvent(new String("我们发布的事件")) {

        });
        context.publishEvent(new ApplicationEvent(new Integer(1)) {

        });
        context.close();
    }
}
