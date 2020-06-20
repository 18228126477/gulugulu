package com.xmr.demo.study.thread;

import java.util.concurrent.CountDownLatch;

public class a {
    public static void main(String[] args){
        //CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。比如有一个任务A，
        //它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
        //通俗一点的说 就是 使用CountDownLatch 时  几个线程使用同一个CountDownLatch对象 有一个线程调用wait方法
        //其他线程使用countDown()方法，当最后计数器减到0的时候，wait调用处 继续往下执行；
        //CountDownLatch类只提供了一个构造器：
        final CountDownLatch latch = new CountDownLatch(2);
        for(int i=0;i<=1;i++){
            new Thread(() -> {
                try{
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
