package com.xmr.demo.service.alipayService.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xmr.demo.param.AlipayOrderParam;
import com.xmr.demo.param.AlipayParam;
import com.xmr.demo.service.BaseService;
import com.xmr.demo.service.alipayService.AliPayService;
import com.xmr.demo.untils.CustomAnnotationScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AlipayServiceImpl extends BaseService implements AliPayService  {

    private static final Logger logger = LoggerFactory.getLogger(CustomAnnotationScan.class);

    @Value("${ali_return_url}")
    private String ali_return_url;

    @Value("${ali_notify_url}")
    private String ali_notify_url;

    @Value("${product_code}")
    private String product_code;

    @Value("${timeout_express}")
    private String timeout_express;

    @Value("${url}")
    private String url;

    @Value("${appid}")
    private String appid;

    @Value("${private_key}")
    private String private_key;

    @Value("${ali_public_key}")
    private String ali_public_key;

    @Value("${sign_type}")
    private String sign_type;

    @Override
    public String alipay(AlipayParam alipayParam) {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid, private_key, "json", "UTF-8", ali_public_key, sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(ali_return_url);
        alipayRequest.setNotifyUrl(ali_notify_url);
        AlipayOrderParam alipayOrderParam = new AlipayOrderParam();
        alipayOrderParam.setOut_trade_no(alipayParam.getOrderId());//唯一标识
        alipayOrderParam.setSubject(alipayParam.getOrderName());
        alipayOrderParam.setTotal_amount(alipayParam.getAmount());
        alipayOrderParam.setProduct_code(product_code);
        alipayOrderParam.setTimeout_express(timeout_express);
        alipayRequest.setBizContent(JSON.toJSONString(alipayOrderParam));
        String webForm = "";
        try {
            webForm = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            logger.error("支付请求发送失败");
        }
        return webForm;
    }

    @Override
    public String synchronous(HttpServletRequest request) {
        return null;
    }

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {

    }
}
