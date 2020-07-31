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
