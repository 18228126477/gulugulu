package com.xmr.demo.study.demoApplicationContext;

import org.springframework.context.annotation.*;

@Configuration
public class MainConfig2 {

    /**
     * @Scope
     * spring注入的对象默认是单实例
     * prototype 是多实例 ioc容器会在每次获取对象时才会调用方法创建对象。
     * singleton 是单实例 默认 ioc容器启动会调用方法创建对象放到容器中，以后每次获取直接从容器中拿（beanDefinitionMap中.get）
     * request 同一个请求创建一个实例
     * session 同一个session创建一个实例
     * */
    /**
     * @Lazy
     * 通过懒加载创建bean:（针对单实例）容器启动时不创建对象，在第一次使用bean时创建对象
     * */
    @Scope("singleton")
    @Lazy
    @Bean(name = "person2")
    public Person person(){
        System.out.println("person2被创建了。。。。。。。。。。。");
        return new Person("lisi",25);
    }

    /**
     * @Conditional （在springBoot底层大量使用）需要传入一个实现了Condition接口的类
     * 按照一定条件，满足条件的才给容器中注册Bean
     * 如果是windows 就往容器中放入wangwu;
     * 如果是linux 就往容器中放入zhaoLiu
     * */
    @Conditional({})
    @Bean(name = "wangwu")
    public Person person01(){
        return new Person("wangwu",25);
    }

    @Bean(name = "zhaoLiu")
    public Person person02(){
        return new Person("zhaoLiu",25);
    }
}