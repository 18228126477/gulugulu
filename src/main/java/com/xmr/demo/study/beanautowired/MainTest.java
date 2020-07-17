package com.xmr.demo.study.beanautowired;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class MainTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("test");
        context.register(MainConfigProfile.class);
        context.refresh();
        String[] names = context.getBeanNamesForType(DataSource.class);
        for(String name:names){
            System.out.println(name);
        }
    }
}
