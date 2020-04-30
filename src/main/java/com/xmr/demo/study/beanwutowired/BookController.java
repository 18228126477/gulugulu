package com.xmr.demo.study.beanwutowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    public BookService bookService;

}
