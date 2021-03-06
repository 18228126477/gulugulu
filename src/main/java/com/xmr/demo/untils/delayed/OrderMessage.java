package com.xmr.demo.untils.delayed;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderMessage implements Delayed {
    
    private final static long DELAY = 2000L;//默认延迟15分钟
    
    private final String orderId;//订单号
    
    private final long startTime ;//开始时间

    private final long expire ;//到期时间
    
    private final Date now; //创建时间
    
    private final String orderMsg;//订单其他信息JSON方式保存，备用字段
    
    public OrderMessage(String orderId, String startTimeStr14 ,long secondsDelay) {
        super();
        this.orderId = orderId;
        this.startTime = System.currentTimeMillis();
        this.expire = startTime + (secondsDelay*1000L);
        this.now = new Date();
        this.orderMsg="";
    }
    
    public OrderMessage(String orderId, String startTimeStr14, String orderMsg ,long secondsDelay) {
        super();
        this.orderId = orderId;
        this.startTime = System.currentTimeMillis();
        this.expire = startTime + (secondsDelay*1000L);
        this.orderMsg = orderMsg;
        this.now = new Date();
    }
    
    public OrderMessage(String orderId, String startTimeStr14) {
        super();
        this.orderId = orderId;
        this.startTime = System.currentTimeMillis();
        this.expire = startTime + DELAY;
        this.now = new Date();
        this.orderMsg="";
    }
    
    public OrderMessage(String orderId, String startTimeStr14, String orderMsg) {
        super();
        this.orderId = orderId;
        this.startTime = System.currentTimeMillis();
        this.expire = startTime + DELAY;
        this.orderMsg = orderMsg;
        this.now = new Date();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderMsg() {
        return orderMsg;
    }
    
    public Date getNow() {
        return now;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getExpire() {
        return expire;
    }

}