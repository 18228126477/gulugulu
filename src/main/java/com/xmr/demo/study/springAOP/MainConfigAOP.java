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
 *              2）给容器中添加别的BeanPostProcessors
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
 *                      4.类型为AnnotationAwareAspectJAutoProxyCreator的BeanPostProcessors创建成功
 *              7）调用beanFactory.addBeanPostProcessor(postProcessor);把postProcessor注册到beanFactory中
 * ------------------------------------以上就是创建AnnotationAwareAspectJAutoProxyCreator这个后置处理器的过程------------------------------
 *          4.finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)：完成BeanFactory初始化工作，注册剩下的单实例bean
 *  *              AnnotationAwareAspectJAutoProxyCreator该后置处理器会在创建bean之前调用InstantiationAwareBeanPostProcessor接口下的postProcessBeforeInstantiation()尝试返回对象
 *  *             1）便利获取容器中所用的Bean，依次创建对象
 *  *                  getBean()-》doGetBean()-》getSingleton()
 *  *             2）创建Bean
 *  *                  【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *  *                  【InstantiationAwareBeanPostProcessor是创建Bean实例之前先尝试用后置处理器返回对象】
 *  *                  1.先从缓存中获取当前bean，如果能获取到，说明被创建了，直接使用，否者开始创建
 *  *                  2.如果没有创建则调用createBean()创建bean
 *  *                      1）resolveBeforeInstantiation(beanName, mbdToUse)；解析BeforeInstantiation
 *  *                          希望后置处理器在此能返回一个代理对象，如果能返回代理对象就是用，如果不能返回就继续
 *  *                          1.后置处理器先尝试返回对象
 *  *                              bean = this.applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *  *                                  拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行
 *  *                              if (bean != null) {
 *  *                                  bean = this.applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *  *                              }
 *  *                      2）如果没有代理对象则使用doCreateBean(beanName, mbdToUse, args)创建一个bean
 *  *------------------------------------------------------------------
 * 该后置处理器属于【InstantiationAwareBeanPostProcessor】类型的作用：
 *  1）每一个bean在创建之前都会调用postProcessBeforeInstantiation()方法返回一个代理对象
 *      1.判断当前bean是否在advisedBeans中（保存了所有需要增强的bean）
 *      2.判断当钱bean是否是基础类型，基础类型包括【Advice，Pointcut，Advisor，AopInfrastructureBean】这些接口下，或者是否是切面【AspectJAdvisorFactory】
 *      3.判断是否需要跳过【返回false，跳过】
 *          1）获取候选得增强器（增强器是指切面里得通知方法）List<Advisor> advisors = super.findCandidateAdvisors();
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *              判断每一个增强器的类型是否是AspectJPointcutAdvisor：返回true
 *   2）创建完对象后调用postProcessAfterInitialization()
 *          return this.wrapIfNecessary(bean, beanName, cacheKey);包装如果需要得情况下
 *          1.获取当前bean的所有增强器（通知方法）【封装成了一个Object[] specificInterceptors】
 *              1）找到所有能使用的合格的增强器（找到那些通知方法是需要切入当前bean方法的）
 *              2）获取到能在当前bean使用的合格的增强器
 *              3）给增强器排序
 *           2.保存当前bean到advisedBeans中【true表示当前bean已经被处理了】
 *           3.如果当前bean需要增强，创建当前bean的代理对象
 *              1）获取所有的增强器（通知方法）
 *              2）将增强器保存到工厂
 *              3）创建AOP代理对象【有两种代理对象，一种是(jdk)JdkDynamicAopProxy(config)，一种是(cglib)new ObjenesisCglibAopProxy(config)】
 *           4.给容器中添加一个cglib增强后的代理对象【以后容器中获取到的就是这个组件的代理对象，执行目标方法时，代理对象就会执行通知方法】
 *-----------------------------------------以上是创建AOP代理对象【通过cglib或jdk增强后的代理对象】的流程------------------------------------
 *     3）目标方法执行
 *          容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象等）
 *          1.CglibAopProxy.intercept()；拦截目标方法的执行
 *          2.根据advised【ProxyFactory】对象获取将要执行的目标方法的拦截器链
 *               List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *               1）创建一个List<Object> interceptorList保存所有的拦截器
 *               2）遍历所有的增强器，将其转为Interceptor[] interceptors【通过registry.getInterceptors(advisor)】
 *               3）将增强器转为List<MethodInterceptor> interceptors
 *                  如果是MethodInterceptor，直接加入到集合中
 *                  如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor
 *                  最后返回MethodInterceptor数组
 *          3.如果没有拦截器链，直接执行目标方法
 *              拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *          4.如果有拦截器链，把需要执行的代理对象，目标对象，目标方法，拦截器链等信息传入，创建一个CglibMethodInvocation并调用proceed()方法
 *          5.拦截器链的触发过程
 *              1）如果没有没有拦截器直接执行目标方法，或者拦截器的索引和拦截器的数组-1一样（执行到最后一个拦截器）执行目标方法
 *              2）链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等到下一个拦截器执行完返回以后再来执行
 *                  拦截器链保证通知方法和目标方法的执行顺序
 * 总结：
 *      1.@EnableAspectJAutoProxy开启AOP功能
 *      2.@EnableAspectJAutoProxy会给容器中注入一个AnnotationAwareAspectJAutoProxyCreator
 *      3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *      4.容器创建流程：
 *          1）registerBeanPostProcessors(beanFactory)注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator对象
 *          2）finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)初始化剩下的单实例bean
 *              1.创建业务逻辑组件和切面组件
 *              2.AnnotationAwareAspectJAutoProxyCreator拦截组件创建过程
 *              3.组件创建完成后，postProcessBeforeInstantiation()判断组件是否需要增强
 *                  是，切面的通知方法，包装成增强器(Advisor)；给业务逻辑组件创建一个代理对象
 *      5.执行目标方法
 *          1）代理对象执行目标方法
 *          2）CglibAopProxy.intercept()进行拦截
 *              1.得到目标方法的拦截器链（增强器包装成拦截器）
 *              2.利用拦截器的链式机制，依次进入每个拦截器进行执行
 *              3.效果
 *                  正常执行：前置通知-》目标方法-》后置通知-》返回通知
 *                  出现异常：前置通知-》目标方法-》后置通知-》异常通知
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
