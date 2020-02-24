package com.xmr.demo.untils.delayed;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.*;

public class DelayOrderComponent {

    private static DelayQueue<OrderMessage> delayQueue =  new DelayQueue<>();

    /**加入延迟消息队列**/
    private boolean addToOrderDelayQueue(OrderMessage orderMessage){
        return delayQueue.add(orderMessage);
    }


    /**从延迟队列中移除**/
    private void removeToOrderDelayQueue(OrderMessage orderMessage){
        for (Iterator<OrderMessage> iterator = delayQueue.iterator(); iterator.hasNext();) {
            OrderMessage queue = iterator.next();
            if(orderMessage.getOrderId().equals(queue.getOrderId())){
                delayQueue.remove(queue);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final DelayQueue<OrderMessage> delayQueue =  new DelayQueue<>();

        delayQueue.add(new OrderMessage(""+1, "1","3秒后执行"));
        delayQueue.add(new OrderMessage(""+2, "1","4秒后执行"));
        delayQueue.add(new OrderMessage(""+3, "1","8秒后执行"));
        new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(32), new ThreadPoolExecutor.CallerRunsPolicy())
            .execute(() -> {
                OrderMessage message = null;
                while (true) {
                    try {
                        message = delayQueue.take();
                        System.out.println(new Date()+"  处理延迟消息:  "+ JSON.toJSONString(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
        System.out.println("此处有新订单");
        Thread.sleep(2000L);
        delayQueue.add(new OrderMessage(""+4, "1","8秒后执行"));
    }
}
