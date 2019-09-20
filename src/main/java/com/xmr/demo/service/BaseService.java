package com.xmr.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xmr.demo.untils.redis.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaseService {

    @Autowired
    public RedisUntil redisUntil;

    private String SIGN = "sign";
    private String SUCCESS = "success";
    private String ERR = "err";
    private String DATA = "data";

    public String doSuccessMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        return JSONObject.toJSONString(result);
    }

    public String doSuccess(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }

    public String doErrMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,ERR);
        return JSONObject.toJSONString(result);
    }

    public String doErr(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,ERR);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }

    public boolean isEmpty(Object obj){
        if (obj == null) {
            return true;
        }else if(obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }else if(obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }
}
