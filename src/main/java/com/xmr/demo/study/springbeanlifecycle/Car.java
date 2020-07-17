package com.xmr.demo.study.springbeanlifecycle;

import org.springframework.beans.factory.annotation.Value;

/**
 * 使用@Value赋值;
 * 1、基本数值
 * 2、可以写SpEL; #{}
 * 3、可以写${};取出配置文件[properties]中的值(在运行环境变量里面的值) 通过@PropertySource注解，在配置类里读取
 * */
public class Car {

    @Value("30000000")
    private Integer price;

    @Value("#{20-3}")
    private String age;

    @Value("${car.name}")
    private String name;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

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
