package com.xmr.demo.service.alipayService;

import com.xmr.demo.param.AlipayParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AliPayService {

    /**
     * 付款
     * @param alipayParam 付款参数
     * @return 付款返回值
     */
    String alipay(AlipayParam alipayParam);

    /**
     * 付款同步返回地址
     * @param request
     * @return
     */
    String synchronous(HttpServletRequest request);

    /**
     * 付款异步通知调用地址
     * @param request 新增参数
     * @return 新增返回值
     */
    void notify(HttpServletRequest request, HttpServletResponse response);
}
