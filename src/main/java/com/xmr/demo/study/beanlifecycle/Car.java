package com.xmr.demo.study.beanlifecycle;

public class Car {

    public Car() {
        System.out.println("Car被创建..............");
    }

    public void init(){
        System.out.println("Car正在init..............");
    }

    public void destroy(){
        System.out.println("Car已被destroy..............");
    }
}
