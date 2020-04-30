package com.xmr.demo.study.beanwutowired;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bookService = context.getBean(BookService.class);
        bookService.print();
    }
}
