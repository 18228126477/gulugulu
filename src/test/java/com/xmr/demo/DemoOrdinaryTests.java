package com.xmr.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DemoOrdinaryTests {
    public static void main(String[] args){
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
