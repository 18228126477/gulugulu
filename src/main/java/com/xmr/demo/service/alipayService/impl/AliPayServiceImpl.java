package com.xmr.demo.service.alipayService.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xmr.demo.param.AlipayOrderParam;
import com.xmr.demo.param.AlipayParam;
import com.xmr.demo.service.alipayService.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@PropertySource("classpath:/aliPay.properties")
public class AliPayServiceImpl implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Value("${ali_return_url}")
    private String aliReturnUrl;

    @Value("${ali_notify_url}")
    private String aliNotifyUrl;

    @Value("${product_code}")
    private String productCode;

    @Value("${timeout_express}")
    private String timeoutExpress;

    @Value("${url}")
    private String url;

    @Value("${appid}")
    private String appid;

    @Value("${private_key}")
    private String privateKey;

    @Value("${ali_public_key}")
    private String aliPublicKey;

    @Value("${sign_type}")
    private String signType;


    @Override
    public String pay(AlipayParam alipayParam) {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid, privateKey, "json", "UTF-8", aliPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliReturnUrl);
        alipayRequest.setNotifyUrl(aliNotifyUrl);
        AlipayOrderParam alipayOrderParam = new AlipayOrderParam();
        alipayOrderParam.setOut_trade_no(alipayParam.getOrderId());//唯一标识
        alipayOrderParam.setSubject(alipayParam.getOrderName());
        alipayOrderParam.setTotal_amount(alipayParam.getAmount());
        alipayOrderParam.setProduct_code(productCode);
        alipayOrderParam.setTimeout_express(timeoutExpress);
        alipayRequest.setBizContent(JSON.toJSONString(alipayOrderParam));
        String webForm = "";
        try {
            webForm = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            logger.error("支付请求发送失败");
        }
        return webForm;
    }

    public String synchronous(HttpServletRequest request) {
        return null;
    }

    public void notify(HttpServletRequest request, HttpServletResponse response) {}
}
