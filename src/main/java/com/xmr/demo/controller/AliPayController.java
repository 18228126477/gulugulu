package com.xmr.demo.controller;

import com.xmr.demo.param.AlipayParam;
import com.xmr.demo.service.alipayService.AliPayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("alipay")
public class AliPayController extends BaseController{
    @Resource
    private AliPayService alipayService;

    //1.申请付款
    @ResponseBody
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String alipay(AlipayParam alipayParam){
        return alipayService.alipay(alipayParam);
    }

    //2.a1ipay同步通知调用地址
    @RequestMapping(value = "/getReturnUrlInfo",method = RequestMethod.GET)
    public String alipayReturnUrlInfo(HttpServletRequest request) {
        return alipayService.synchronous(request);
    }

    //3.alipay异步通知调用地址
    @RequestMapping(value = "/getNotifyUrlInfo",method = RequestMethod.POST)
    public void alipayNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response){
        alipayService.notify(request,response);
    }
}
