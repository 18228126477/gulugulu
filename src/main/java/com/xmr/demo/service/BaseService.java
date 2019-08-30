package com.xmr.demo.service;

import com.xmr.demo.untils.redis.impl.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService {
    
    @Autowired
    public RedisUntil redisUntil;

    public void testWeightForArray(List<Object> arr,Object value){

    }
}
