package com.xmr.demo.study.beanapplicationcontext;

import org.springframework.context.annotation.*;

/**
 * 该注解标志这是一个配置类
 * */
@Configuration
/**
 * @ComponentScan
 * 通过扫描包的方式给ioc容器中注入组件(会把含有@controller,@service,@component,@repository之一的组件注入ioc容器)
 * 并且可以指定扫描的包，也可以排除某些包，需要指定包含包是还要使 useDefaultFilters = false
 * FilterType.ANNOTATION: 按照注解
 * FilterType.ASSIGNABLE_TYPE:按照给定的类型
 * FilterType.ASPECTJ:使用ASPECTJ表达式
 * FilterType.REGEX:使用正则指定
 * FilterType.CUSTOM:使用自定义
 */
/**
 * @ComponentScan(value = "com.xmr.demo.study.demoapplicationcontext",
 *         excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
 * */
/**
 * 可以使用@ComponentScans多指定几个@ComponentScan来扫面组件
 * */
/**
 * @ComponentScans(value = {
 *         @ComponentScan(value = "com.xmr.demo.study.demoapplicationcontext",
 *                 excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
 * })
 * */
/**
 * 自定义扫描路劲
 * */
 @ComponentScan(value = "com.xmr.demo.study.beanapplicationcontext",
         includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {FilterCustom.class})},useDefaultFilters = false)
public class MainConfig {
    /**
     * 给容器中注入一个bean，bean的类型就是返回值的类型，默认名字是方法名，可通过注解name属性修改bean的名字
     * */
    @Bean(name="person1")
    public Person person(){
        return new Person("zhangsan",18);
    }
}
