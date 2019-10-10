package com.xmr.demo.untils.until;

public class Cipher {

    private static final int max = 1<<18;
    private static final int min = 1;

    public static int pageId(){
        long randomNum = System.currentTimeMillis();
        return  (int) (randomNum%(max-min)+min);
    }
}
