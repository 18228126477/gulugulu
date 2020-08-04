package com.xmr.demo.study.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigAOP.class);
        MathCalculator mathCalculator = context.getBean(MathCalculator.class);
        mathCalculator.div(1,1);
    }
}
