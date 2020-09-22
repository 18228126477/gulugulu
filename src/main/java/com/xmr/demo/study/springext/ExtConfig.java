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
 *          6.再从容器中找到BeanFactoryPostProcessor组件，然后依次触发postProcessBeanFactory
 * BeanDefinitionRegistry：bean定义信息的保存中心，以后beanFactory就是按照BeanDefinitionRegistry保存的bean定义信息创建bean实例；
 * ApplicationListener【事件监听器】：监听容器中发布的事件，完成事件驱动
 *      public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *      监听ApplicationEvent及其下面的子事件
 *      步骤：
 *          1.写一个监听器监听某个事件(ApplicationEvent及其子类)
 *              或者使用@EventListener
 *              原理：使用EventListenerMethodProcessor处理器来解析方法上的@EventListener
 *          2.把监听器加入容器
 *          3.只要容器中有关事件的发布，我们就能监听到这个事件
 *              比如：ContextRefreshedEvent容器刷新事件
 *                    ContextClosedEvent容器关闭事件
 *          4.发布一个事件：
 *              applicationContext.publishEvent
 *       底层流程：
 *          1）ContextRefreshedEvent
 *              1.容器创建并执行refresh()
 *              2.调用finishRefresh()方法；容器刷新完成
 *              3.publishEvent(new ContextRefreshedEvent(this))；发布事件
 *                  事件发布流程：
 *                      1）获取事件的多播器（派发器）：getApplicationEventMulticaster()
 *                      2）派发事件：multicastEvent(applicationEvent, eventType)
 *                      3）获取到所有的ApplicationListener
 *                          for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                          1.如果有Executor，可以支持使用Executor进行异步派发
 *                          2.如果没有，同步的方式直接执行listener方法：invokeListener(listener, event)
 *                          最后拿到listener回调listener.onApplicationEvent(event)方法
 *          2）自己的事件和ContextRefreshedEvent一样
 *          3）ContextClosedEvent
 *              1.调用的是close()方法
 *              2.其他步骤和ContextRefreshedEvent一样
 * 事件多播器【派发器】
 *      1）容器创建调用refresh()
 *      2）initApplicationEventMulticaster()初始化事件多播器
 *          1.先去容器中找有没有id="applicationEventMulticaster"的组件
 *          2.如果没有new SimpleApplicationEventMulticaster(beanFactory)，并且注册到容器中
 * 容器中有哪些监听器
 *      1）容器创建调用refresh()
 *      2）registerListeners()注册监听器
 *          从容器中拿到所有的监听器，并把他们注册到applicationEventMulticaster
 *          String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 * 		    for (String listenerBeanName : listenerBeanNames) {
 * 		        //将listener注册到ApplicationEventMulticaster中
 * 			    getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *          }
 * SmartInitializingSingleton原理：
 *      1）创建容器
 *      2）调用refresh();
 *      3)finishBeanFactoryInitialization(beanFactory)；初始化剩下的单实例bean
 *          1.遍历先调用getBean()创建所有的单实例bean
 *          2.遍历获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的
 *              如果是就调用afterSingletonsInstantiated()方法
 * */
@ComponentScan(value="com.xmr.demo.study.springext")
@Configuration
public class ExtConfig {

    public Bmw bmu(){
        return new Bmw();
    }
}
