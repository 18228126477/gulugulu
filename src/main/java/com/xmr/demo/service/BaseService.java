package com.xmr.demo.service;

import com.xmr.demo.untils.redis.RedisUntil;
import com.xmr.demo.untils.until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaseService {

    @Autowired
    public RedisUntil redisUntil;

    private Result result = new Result();

    public String doSuccess(Object data){
        return result.doSuccess(data);
    }

    public String doSuccess(){
        return result.doSuccessMsg();
    }

    public String doErr(Object data){
        return result.doErr(data);
    }

    public String doErr(){
        return result.doErrMsg();
    }

    protected Date getNowDate(){
        return new Date();
    }

    protected Boolean checkTime(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, +5);
        Date newDate = new Date();
        if(newDate.after(calendar.getTime())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    protected boolean isEmpty(Object obj){
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
