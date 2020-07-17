package com.xmr.demo.study.springbeanapplicationcontext;

import org.springframework.context.annotation.*;

@Configuration
/**
 * 给容器注册组件的方式
 * 1.包扫描+组件注解（@controller,@service,@component,@repository）
 * 2.@Bean 当导入的是第三方包时使用，比如集成redis，mybatis
 * 3.@Import 快速给容器中注入一个组件
 *      1.在类上引入@Import注解，并且写入需要注入得组件
 *      2.使用ImportSelector接口，返回的数组就是需要注入的组件的全类名
 *      3.使用ImportBeanDefinitionRegistrar接口，可以自定义的注册bean定义
 *      调用顺序，首先注册的事ImportSelector接口的实现类的组件，再注册ImportBeanDefinitionRegistrar实现类的组件
 * */
@Import({ImportBean.class,MyImportBeanDefinitionRegistrar.class,MyImportSelector.class})
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
        return new Person("lisi",25);
    }

    /**
     * @Conditional （在springBoot底层大量使用）需要传入一个实现了Condition接口的类
     * 按照一定条件，满足条件的才给容器中注册Bean
     * 可以放在方法上
     * 可以放在类上
     * */
    @Conditional({OSConditiona.class})
    @Bean(name = "wangwu")
    public Person person01(){
        return new Person("wangwu",25);
    }

    @Conditional({OSConditiona.class})
    @Bean(name = "zhaoLiu")
    public Person person02(){
        return new Person("zhaoLiu",25);
    }


    /**
     * 使用Spring提供的FactoryBean (工厂Bean) ;
     * 1)、默认获取到的是工厂bean调用getObject创建的对象
     * 2)、要获取工厂Bean本身，我们需要给id前面加一个&
     *      &colorFactoryBean
     * */
    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }
}
