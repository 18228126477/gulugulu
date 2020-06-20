package com.xmr.demo.study.beanlifecycle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    /**
     * bean的初始化和销毁
     * */
    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigLifeCycle.class);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
        //关闭ioc容器
        applicationContext.close();
    }
}
