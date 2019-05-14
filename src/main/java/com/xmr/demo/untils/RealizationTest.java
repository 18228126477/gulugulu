package com.xmr.demo.untils;

import com.xmr.demo.common.AbnormalEnum;
import com.xmr.demo.common.annotation.MethodSign;
import com.xmr.demo.untils.impl.AnnotationInterface;
import com.xmr.demo.untils.redis.RedisUntilImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

public class RealizationTest implements AnnotationInterface {

    private static final Logger logger = LoggerFactory.getLogger(CustomAnnotationScan.class);

    private RedisUntilImpl redisUntilImpl = new RedisUntilImpl();

    @Override
    public void geMethodWithAnnotationFromFilePath(Class<?> clz) {
        try{
            Class<?> aClass = Class.forName(clz.getName());
            Method[] methods = aClass.getMethods();
            RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
            String value = annotation.value()[0];
            for(Method method:methods){
                if(method.isAnnotationPresent(MethodSign.class)){
                    RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                    MethodSign methodSign = method.getAnnotation(MethodSign.class);
                    try{
                        for(String s :annotation1.value()){
                            redisUntilImpl.set("/"+value+"/"+s,"/"+methodSign);
                        }
                    }catch (NullPointerException e){
                        logger.error("redis服务器未开启"+ e.toString() + e.getMessage());
                    }
                }
            }
        }catch (ClassNotFoundException e){
            logger.error(clz + AbnormalEnum.ONE.getDesc()+ e.toString() + e.getMessage());
        }
    }
}
