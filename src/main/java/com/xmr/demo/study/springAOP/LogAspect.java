package com.xmr.demo.study.springAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspect {

    /**
     * 抽取公共的切入点
     * 1.本类引用：如@Before("pointCut()")
     * 2.其他类引用：如@Before("com.xmr.demo.study.springAOP.LogAspect.pointCut()")
     * */
    @Pointcut("execution(public Integer com.xmr.demo.study.springAOP.MathCalculator.div(Integer,Integer))")
    public void pointCut(){

    }

    /**
     * 如果想给某个类下所有的方法进行切面则用*加..
     * JoinPoint：可以获取方法名，方法参数【该参数一定要出现在参数表的第一位】
     * */
    @Before("execution(public Integer com.xmr.demo.study.springAOP.MathCalculator.*(..))")
    public void logStart(JoinPoint joinPoint){
        System.out.println(""+joinPoint.getSignature().getName()+"除法开始。。。。参数列表{"+ Arrays.asList(joinPoint.getArgs()) +"}");
    }

    @After("pointCut()")
    public void logEnd(){
        System.out.println("除法结束。。。。参数列表{}");
    }

    /**
     * 正常返回获取返回值
     * */
    @AfterReturning(value = "pointCut()",returning = "object")
    public void logReturn(Object object){
        System.out.println(""+object+"除法正常运行");
    }

    /**
     * 出现异常获取异常
     * */
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logErr(Exception exception){
        System.out.println("除法异常运行"+exception);
    }
}
