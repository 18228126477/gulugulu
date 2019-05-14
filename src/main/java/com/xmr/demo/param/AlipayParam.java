package com.xmr.demo.param;

import org.springframework.web.servlet.tags.Param;

public class AlipayParam extends Param {
    /*金额*/
    private String amount;
    /*订单名称*/
    private String orderName;
    /*订单号唯一标识*/
    private String orderId;

    public String getAmount() {
        return amount;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
