package com.xmr.demo.study.springext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        String[] beanDefinitionNames = beanDefinitionRegistry.getBeanDefinitionNames();
        int beanDefinitionCount = beanDefinitionRegistry.getBeanDefinitionCount();
        System.err.println("BeanDefinitionRegistry有"+beanDefinitionCount+"个beanDefinition");
        System.err.println("BeanDefinitionRegistry"+ Arrays.asList(beanDefinitionNames));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
