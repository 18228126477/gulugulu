package com.xmr.demo.study.springbeanautowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 自动装配;
 *          spring利用依赖注入(DI) ，完成对IOC容器中中各个组件的依赖关系赋值;
 *
 * @Autowired: 自动注入:
 * 1)默认优先按照类型去容器中找对应的组件: applicationContext.getBean(BookDao.class);
 * 2)如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找applicationContext.getBean("bookDao")
 * 3)@Qualifier("bookDao"): 使用@Qualifier指定需要装配的组件的id,而不是使用属性名
 * 4)自动装配默认一定要讲属性赋值好，没有就会报错
 *   可以使用@Autowired(required=false)；使其不用赋值好
 * 5)@Primary:让Spring进行自动装配的时候，默认使用首选的bean
 *   也可以继续使用@Qualifier指定需要装配的bean的名字
 *  Spring还支持使用@Resource(JSR250)和@Inject(330)
 *      @Resource:
 *               可以和@Autowired一样完成自动装配功能，默认是按照组件名称进行装配的。不可以使用@Qualifier和@Primary注解，
 *               但可以使用@Resource(name = "bookDao2")指定需要装配的主键id
 *      @Inject：
 *               需要导入javax.inject的包，和@Autowired基本一样，只是没有required = false
 *   AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能
 *
 * @Autowired: 能标注的位置有构造器，参数，方法，属性；都是从容器中获取参数组件的值
 *      标注在构造器上时：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
 *      标注在方法，Spring容器创建当前对象，就会调用方法，完成赋值;
 *      方法使用的参数，自定义类型的值从ioc容器中获取
 * Aware接口：自定义组件想要使用Spring容器底层的一些组件，比如（ApplicationContext,BeanFactory,等等）测试例:CarDao
 *      自定义组件实现xxxAware：在创建对象的时候，会调用接口规定的方法注入相关组件
 *      把Spring底层一些组件注入到自定义bean中；
 *      xxxAware，功能使用xxxProcessor;
 *          ApplicationContextAware=》ApplicationContextAwareProcessor;
 *
 */


@Configuration
@ComponentScan({"com.xmr.demo.study.springbeanautowired"})
public class MainConfigOfAutowired {

    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setName("时间控制");
        return bookDao;
    }
}
