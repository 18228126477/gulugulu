package com.xmr.demo.controller;

import com.xmr.demo.common.PayFactory;
import com.xmr.demo.param.AlipayParam;
import com.xmr.demo.service.alipayService.PayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("pay")
public class AliPayController extends BaseController{

    //1.申请付款
    @ResponseBody
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String pay(AlipayParam alipayParam,String type){
        PayService payStrategy = PayFactory.getInstance().getPayStrategy(type);
        return payStrategy.pay(alipayParam);
    }
}
