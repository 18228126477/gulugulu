package com.xmr.demo.untils.until;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private final String FLAG = "flag";
    private final Boolean SUCCESS = Boolean.TRUE;
    private final Boolean ERR = Boolean.FALSE;
    private final String DATA = "data";

    public String doSuccessMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(FLAG,SUCCESS);
        return JSONObject.toJSONString(result);
    }

    public String doSuccess(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(FLAG,SUCCESS);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }

    public String doErrMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(FLAG,ERR);
        return JSONObject.toJSONString(result);
    }

    public String doErr(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(FLAG,ERR);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }
}
