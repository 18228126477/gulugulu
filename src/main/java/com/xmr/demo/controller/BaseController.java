package com.xmr.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {

    private String SIGN = "sign";
    private String SUCCESS = "success";
    private String DATA = "data";

    String doSuccessMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        return JSONObject.toJSONString(result);
    }

    String doSuccess(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }
}
