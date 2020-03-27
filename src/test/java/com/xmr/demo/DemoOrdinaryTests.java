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
        List<Integer> arr= Arrays.asList(3,1,15,2,9,6,3,2,7,1);
        List<Integer> result= new ArrayList<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new SynchronousQueue<>());
        arr.forEach(n-> pool.execute(() -> {
            try {
                Thread.sleep(n*100);
                result.add(n);
                System.out.println(n);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }));
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
