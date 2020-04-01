package com.xmr.demo.study.demoApplicationContext;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class MainTest {

    /**
     * 两种方式给ioc容器注入bean
     * */
    @Test
    public void main1() {
        //通过xml文件方式给spring容器注入bean
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //通过注解的方式给spring容器注入一个bean
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        //通过类型获取容器中的bean
        Person person = configApplicationContext.getBean(Person.class);
        System.out.println(person);
        //通过类型获取容器中该类型的所有bean的名字
        String[] namesForType = configApplicationContext.getBeanNamesForType(Person.class);
        for(String name:namesForType){
            System.out.println(name);
        }
        //获取bean定义的名字
        String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println("beanDefinition======"+name);
        }
        //通过名字获取容器中的bean
        Person person1 = (Person)configApplicationContext.getBean("person1");
        System.out.println(person1);
    }

    /**
     * @scope
     * 设置组件作用域
     * */
    @Test
    public void main2(){
        //通过注解的方式给spring容器注入一个bean
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        //获取bean定义的名字
        String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println("beanDefinition======"+name);
        }
        Person bean1 = (Person)configApplicationContext.getBean("person2");
        Person bean2 = (Person)configApplicationContext.getBean("person2");
        System.out.println(bean1==bean2);
    }

    @Test
    public void main3(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        //获取ioc容器运行环境
        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        Map<String, Person> beansOfType = annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
        ImportFactoryBean bean = annotationConfigApplicationContext.getBean(ImportFactoryBean.class);
        System.out.println(bean.getClass());
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println(name);
        }
    }
}
