package com.xmr.demo.param;

import org.springframework.web.servlet.tags.Param;

public class AlipayOrderParam extends Param {
    private String out_trade_no;//商户订单号
    private String total_amount;//总金额
    private String subject;//订单标题
    private String product_code;//销售产品码，与支付宝签约的产品码名称。
    private String timeout_express;//该笔订单允许的最晚付款时间，逾期将关闭交易。

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public String getProduct_code() {
        return product_code;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }
}