package com.xmr.demo.study.springAOP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP【底层是动态代理】：
 *      指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 1.导入aop模块：Spring AOP：（spring-aspects）
 * 2.定义一个业务逻辑类（MathCalculator），在业务逻辑运行的时候将日志进行打印
 * 3.定义一个日志切面类（），切面类里面的方法需要动态感知MathCalculator.div运行到哪里
 *      通知方法：
 *          前置通知（@Before）：logStart：在目标方法运行之前运行
 *          后置通知（@After）：logEnd：在目标方法运行之后运行 无论方法正常结束还是异常结束
 *          返回通知（@AfterReturning）：logReturn：在目标方法正常运行后运行
 *          异常通知（@AfterThrowing）：logErr：在目标方法出现异常后运行
 *          环绕通知（@Around）：动态代理，手动推进目标方法运行（joinPoint.procced()）
 *  4.给切面类的目标方法标注何时运行
 *  5.将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 *  6.告诉spring哪个类是切面类（给切面类上加个注解）
 *  7.给配置类中加入@EnableAspectJAutoProxy【开启基于注解的AOP模式】
 *  AOP原理：【给容器中注入了什么组件，这个组件什么时候工作，有什么功能】
 *      1.关键注解@EnableAspectJAutoProxy
 *          @Import({AspectJAutoProxyRegistrar.class}),给容器中带入AspectJAutoProxyRegistrar.class
 *              利用AspectJAutoProxyRegistrar自定义给容器中注册bean；
 *              注册了一个internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *      2.AnnotationAwareAspectJAutoProxyCreator
 *          AspectJAwareAdvisorAutoProxyCreator
 *              AbstractAdvisorAutoProxyCreator
 *                      AbstractAutoProxyCreator
 *                          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                          关注后置处理器（bean初始化完成前后做事情），自动装配BeanFactoryAware
 *        AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)
 *        AbstractAutoProxyCreator.后置处理器方法
 *        AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)-》initBeanFactory已把AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)重写
 *      3.流程：
 *          1.创建容器，传入配置类
 *          2.注册配置类，调用refresh()方法刷新
 *          3.调用registerBeanPostProcessors(beanFactory)注册bean得后置处理器来方便拦截bean得创建
 *              1）先获取容器中已经定义了的需要创建得BeanPostProcessors（beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);）
 *              2）给容器中加别的BeanPostProcessors
 *              3）先给容器注册实现PriorityOrdered接口的BeanPostProcessors
 *              4）然后给容器注册实现了Ordered接口的BeanPostProcessors
 *              5）最后给容器注册其他的BeanPostProcessors
 *              6）此时还没有BeanPostProcessors，因此得先注册BeanPostProcessors也就是先创建BeanPostProcessors对象，保存在容器中
 *                  创建名为org.springframework.aop.config.internalAutoProxyCreator得BeanPostProcessors【实际类型为AnnotationAwareAspectJAutoProxyCreator】
 *                      1.创建bean实例
 *                      2.使用populateBean(beanName, mbd, instanceWrapper)给bean得属性赋值
 *                      3.initializeBean(beanName, exposedObject, mbd)初始化bean
 *                          1.invokeAwareMethods(String beanName, Object bean)；处理Aware接口的方法回调
 *                          2.调用applyBeanPostProcessorsBeforeInitialization(bean, beanName)调用bean的后置处理器处理bean初始化之前
 *                          3.invokeInitMethods(beanName, wrappedBean, mbd)；执行初始化方法
 *                          4.调用applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)调用bean的后置处理器处理bean初始化之后
 *                       4.类型为AnnotationAwareAspectJAutoProxyCreator的BeanPostProcessors创建成功
 *               7）调用beanFactory.addBeanPostProcessor(postProcessor);把postProcessor注册到beanFactory中
 * ------------------------------------以上就是创建AnnotationAwareAspectJAutoProxyCreator这个后置处理器的过程-----------------------
 *          4.finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)：完成BeanFactory初始化工作，注册剩下的单实例bean
 *              AnnotationAwareAspectJAutoProxyCreator该后置处理器会在创建bean之前调用InstantiationAwareBeanPostProcessor接口下的postProcessBeforeInstantiation()尝试返回对象
 *              1）便利获取容器中所用的Bean，依次创建对象
 *                  getBean()-》doGetBean()-》getSingleton()
 *              2）创建Bean
 *                  【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *                  【InstantiationAwareBeanPostProcessor是创建Bean实例之前先尝试用后置处理器返回对象】
 *                  1.先从缓存中获取当前bean，如果能获取到，说明被创建了，直接使用，否者开始创建
 *                  2.如果没有创建则调用createBean()创建bean
 *                      1）resolveBeforeInstantiation(beanName, mbdToUse)；解析BeforeInstantiation
 *                          希望后置处理器在此能返回一个代理对象，如果能返回代理对象就是用，如果不能返回就继续
 *                          1.后置处理器先尝试返回对象
 *                              bean = this.applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                                  拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行
 *                              if (bean != null) {
 *                                  bean = this.applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                              }
 *                      2）如果没有代理对象则使用doCreateBean(beanName, mbdToUse, args)创建一个bean
 *AnnotationAwareAspectJAutoProxyCreator该后置处理器属于【InstantiationAwareBeanPostProcessor】类型的作用：
 *  1）每一个bean在创建之前都会调用postProcessBeforeInstantiation()方法返回一个代理对象
 *      1.判断当前bean是否在advisedBeans中（保存了所有需要增强的bean）
 *      2.判断当钱bean是否是基础类型，基础类型包括【Advice，Pointcut，Advisor，AopInfrastructureBean】这些接口下，或者是否是切面【AspectJAdvisorFactory】
 *      3.判断是否需要跳过
 *          1）获取候选得增强器（增强器是指切面里得通知方法）List<Advisor> advisors = super.findCandidateAdvisors();
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *              判断每一个增强器的类型是否是AspectJPointcutAdvisor
 * */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
