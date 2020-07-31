package com.xmr.demo.study.io;

import java.io.*;

public class InputStreamTest {

    public static void filterTest(){
        try{
            FileInputStream fileInputStream = new FileInputStream("C:/Users/p/Desktop/aaa.txt");
            new BufferedInputStream(fileInputStream);
            InputStreamReader is = new InputStreamReader(fileInputStream,"GBK");
            FilterReader br = new PushbackReader(is);
            char[] c = new char[10];
            while(br.read(c)!=-1){
                System.out.println(new String(c));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        filterTest();
    }
}
