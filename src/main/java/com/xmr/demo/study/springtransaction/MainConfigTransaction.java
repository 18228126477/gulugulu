package com.xmr.demo.study.springtransaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 声明式事务
 * 原理:
 *      1)、@EnableTransactionManagement
 *          利用TransactionManagementConfigurationSelector给容器中导入组件
 *          导入两个组件
 *              AutoProxyRegistrar
 *              ProxyTransactionManagementConfiguration
 *      2）、AutoProxyRegistrar:
 *          给容器中注册一个InfrastructureAdvisorAutoProxyCreator 组件，
 *          InfrastructureAdvisorAutoProxyCreator组件
 *          利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行拦截处理
 *      3）、ProxyTransactionManagementConfiguration做了什么?
 *              1、给容器中注册事务增强器;
 *                  1）事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事
 *                  2）事务拦截器:
 *                      TransactionInterceptor;保存了事务属性信息，事务管理器
 *                      他是一个MethodInterceptor
 *                      在目标方法执行的时候：
 *                          执行拦截器链;
 *                          事务拦截器:
 *                              1）先获取事务相关的属性
 *                              2）再获取PlatformTransactionManager，如果事先没有添加指定任何
 *                                  最终会从容器中按照类型获取一个PlatformTransactionManager
 *                              3）执行目标方法
 *                                  如果异常，获取到事务管理器，利用事务管理回滚操作
 *                                  如果正常，利用事务管理器，提交事务
 * */
@EnableTransactionManagement
@ComponentScan()
@Configuration
public class MainConfigTransaction {

}
