package com.xmr.demo.study.springbeanautowired;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @Profile:
 *          spring为我们提供的可以根据当前环境，动态激活的和切换一系列组件功能的注解；
 *          可分为在方法上写注解：此时激活的是有该注解内容匹配的方法。
 *                  类上写注解：此时激活的是有该注解内容配皮的类的所有方法。
 *                  没有写注解的则会被自动注入
 *
 * */


@PropertySource("classpath:/datasource.properties")
@Configuration
public class MainConfigProfile {

    @Value("${datasource.driver.class.name}")
    private String driverClassName;

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Profile("test")
    @Bean
    public DataSource dataSourceTest(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Profile("pro")
    @Bean
    public DataSource dataSourcePro(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        return dataSource;
    }
}
