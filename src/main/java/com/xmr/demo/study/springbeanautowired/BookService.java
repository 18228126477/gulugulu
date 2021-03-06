package com.xmr.demo.study.springbeanautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Qualifier(value = "bookDao")
    @Autowired(required = false)
    //@Resource(name = "bookDao2")
    public BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }
}
