package com.xmr.demo.study.springext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener {

    //当容器中发布此事件以后，方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event.getSource() instanceof String){
            System.out.println(event.getSource());
        }else if(event instanceof ContextRefreshedEvent){
            System.out.println("容器刷新");
        }else if(event instanceof ContextClosedEvent){
            System.out.println("容器关闭");
        }
    }
}
