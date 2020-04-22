package com.xmr.demo.study.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * bean的生命周期：
 *      bean创建----赋值-----初始化-----销毁的过程
 * 容器管理bean的生命周期
 * 自定义初始化和销毁方法：容器在bean进行到当前生命周期的时候调用我们自定义的初始化和销毁方法
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper ) ;给bean进行属性赋值
 * initializeBean 初始化Bean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);执行初始化前操作
 * invokeInitMethods ( beanName，wrappedBean, mbd) ;执行自定义初始化方法
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);执行初始后前操作
 *
 * 构造（对象创建）
 *      单实例：容器启动时创建对象并赋值好，调用初始化方法，在容器关闭时（执行.close()）调用销毁方法
 *      多实例：在每次调用对象时，调用初始化方法，但容器不会管理bean，也就不会调用销毁方法
 *      1.指定初始化销毁方法（相当于xml中的init-method和destroy-method）
 *      2.实现InitializingBean（bean初始化接口）和DisposableBean接口（bean销毁接口）
 *      3.可以使用JSR250;
 *          @PostConstruct:在bean创建完成并且属性赋值完成;来执行初始化方法
 *          @PreDestroy:在容器销毁bean之前通知我们进行清理工作
 *      4.使用BeanPostProcessor接口实现postProcessBeforeInitialization和postProcessAfterInitialization方法(该方法会对所有的自定初始化的bean生效)
 *          1.postProcessBeforeInitialization在bean创建好后初始化之前调用
 *          1.postProcessAfterInitialization在bean创建好后初始化之后调用
 *  
 * */
@PropertySource(value = {"classpath:/dao.properties"})
@ComponentScan("com.xmr.demo.study.beanlifecycle")
@Configuration
public class ConfigLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
