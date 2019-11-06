package com.xmr.demo.common;

import com.xmr.demo.service.alipayService.PayService;
import com.xmr.demo.service.alipayService.impl.AliPayServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class PayFactory {

    private static PayFactory factory = new PayFactory();
    private static Map<String, PayService> map = new HashMap<>();

    static {
        map.put("aliPay", new AliPayServiceImpl());
    }

    public static PayFactory getInstance(){
        return factory;
    }

    public PayService getPayStrategy(String type) {
        return map.get(type);
    }

}
