package com.xmr.demo.study.algorithm;


public class UseArraySubscript {

    public static void  main(String[] args){
        String[] str = {"a","c","a","e","b","c","b","c","s","s","s","g"};
        String s = "adfoweyirlkbasgxalueralsdhg";
        int[] cnt = new int[127];
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            ++cnt[c];
        }
        StringBuilder sb = new StringBuilder();
        for(int i:cnt)
            sb.append(i).append(" ");
        System.out.println(sb.toString());
    }

}
