package com.xmr.demo.common;

import com.xmr.demo.common.annotation.ClassSign;
import com.xmr.demo.untils.CustomAnnotationScan;
import com.xmr.demo.untils.RealizationTest;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringBootInit implements ApplicationRunner {

    private CustomAnnotationScan customAnnotationScan = new CustomAnnotationScan();

    @Override
    public void run(ApplicationArguments args) {
        RealizationTest realizationTest = new RealizationTest();
        List<Class<?>> classesWithAnnotationFromPackage = customAnnotationScan.getClassesWithAnnotationFromPackage("com.cy.controller", ClassSign.class);
        if(classesWithAnnotationFromPackage!=null && !classesWithAnnotationFromPackage.isEmpty()){
            for(Class cla:classesWithAnnotationFromPackage){
                realizationTest.geMethodWithAnnotationFromFilePath(cla);
            }
        }
    }
}
