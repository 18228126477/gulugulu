package com.xmr.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DemoOrdinaryTests {
    public static void main(String[] args){
        List<Integer> arr= Arrays.asList(651,
                865,
                520,
                913,
                77,
                152,
                280,
                279,
                286,
                269,
                298,
                398,
                65,
                101,
                465,
                538,
                733,
                297,
                93,
                193,
                107,
                119,
                186,
                147,
                60,
                131,
                79,
                209,
                115,
                1030,
                161,
                428,
                184,
                79,
                60,
                543,
                86,
                191,
                35,
                109,
                158);
        Integer j = 0;
        for(Integer i:arr){
            j+=i;
        }
        System.out.println(j);
//        List<Integer> result= new ArrayList<>();
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new SynchronousQueue<>());
//        arr.forEach(n-> pool.execute(() -> {
//            try {
//                Thread.sleep(n*100);
//                result.add(n);
//                System.out.println(n);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }));
    }

    public static void data(){
        int i = comparePastDate("2019-11-08", "2019-11-01");
        System.out.println(i);
    }

    public static int comparePastDate(String endDate,String startDate){
        int day=0;
        try {
            //设置转换的日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //开始时间
            Date oldDate = sdf.parse(startDate);
            //结束时间
            Date newDate = sdf.parse(endDate);
            //得到相差的天数 betweenDate
            long betweenDate = (newDate.getTime() - oldDate.getTime())/(60*60*24*1000);
            //打印控制台相差的天数
            day = (int)betweenDate+1;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return day;
    }
}
