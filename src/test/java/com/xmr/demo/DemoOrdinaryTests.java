package com.xmr.demo;

public class DemoOrdinaryTests {
    public static void main(String[] args){
        int max=1<<16,min=1;
        long randomNum = System.currentTimeMillis();
        int ran = (int) (randomNum%(max-min)+min);
        System.out.println(ran);
    }
}
