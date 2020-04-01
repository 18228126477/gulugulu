package com.xmr.demo.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MainClass {

    public static void main(String[] args) {
        testSpi();
    }

    public static void testSpi(){
        //第一步把我们的接口类型保存到ServiceLoader service变量中
        //创建一个懒加载迭代器对象LazyIterator（把我们的接口保存在LazyIterator中）
        ServiceLoader<IParseDoc> load = ServiceLoader.load(IParseDoc.class);
        Iterator<IParseDoc> iterator = load.iterator();
        while (iterator.hasNext()){
            iterator.next().parse();
        }
    };
}
