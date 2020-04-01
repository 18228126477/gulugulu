package com.xmr.demo.study.demoApplicationContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OSConditiona implements Condition {

    /**
     * conditionContext判断条件使用的上下文环境
     * annotatedTypeMetadata当前的类的注释信息
     * */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //获取ioc使用beanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //获取操作系统
        String property = environment.getProperty("os.name");
        //获取bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        boolean person2 = property.contains("Win");
        if(person2){
            return true;
        }
        return false;
    }
}
