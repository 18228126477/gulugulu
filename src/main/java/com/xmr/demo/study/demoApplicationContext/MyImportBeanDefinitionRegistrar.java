package com.xmr.demo.study.demoApplicationContext;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean definition = beanDefinitionRegistry.containsBeanDefinition("com.xmr.demo.study.demoApplicationContext.PersonDao");
        if(!definition){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition("com.xmr.demo.study.demoApplicationContext.PersonDao");
            beanDefinitionRegistry.registerBeanDefinition("PersonDao1",rootBeanDefinition);
        }else{
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition("com.xmr.demo.study.demoApplicationContext.PersonService");
            beanDefinitionRegistry.registerBeanDefinition("PersonService",rootBeanDefinition);
        }
    }
}
