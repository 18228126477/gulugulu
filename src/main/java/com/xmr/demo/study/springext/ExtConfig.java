package com.xmr.demo.study.springext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理
 * BeanPostProcessor：
 *      bean的后置处理器，bean创建对象初始化前后进行拦截工作
 * BeanFactoryPostProcessor：
 *      beanFactory的后置处理器，在beanFactory标准初始化之后拦截工作（所有的bean定义已经保存加载到beanFactory，但bean实例未创建）
 *      1.先创建ioc容器
 *      2.先执行refresh中得invokeBeanFactoryPostProcessors(beanFactory)方法
 *          如何找到BeanFactoryPostProcessor并执行方法的？
 *          1.直接在beanFactory中找到类型为BeanFactoryPostProcessor的组件，并执行他们的方法
 * BeanDefinitionRegistryPostProcessor：
 *      postProcessBeanFactory(ConfigurableListableBeanFactory var1)
 *      在所有bean定义信息将要被加载，bean实例未创建
 *      优于BeanFactoryPostProcessor执行
 *      可以利用BeanDefinitionRegistryPostProcessor再添加一些额外的组件
 *      原理：
 *          1.先创建容器
 *          2.调用refresh()--》invokeBeanFactoryPostProcessors(beanFactory)
 *          3.从容器种获取到所有的BeanDefinitionRegistryPostProcessor组件
 *          4.依次触发所有的postProcessBeanDefinitionRegistry()
 *          5.再依次触发所有的postProcessBeanFactory()
 * BeanDefinitionRegistry：bean定义信息的保存中心，以后beanFactory就是按照BeanDefinitionRegistry保存的bean定义信息创建bean实例；
 * */
@ComponentScan(value="com.xmr.demo.study.springext")
@Configuration
public class ExtConfig {

    public Bmw bmu(){
        return new Bmw();
    }
}
