package com.xmr.demo.service;

import com.xmr.demo.untils.redis.impl.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    
    @Autowired
    public RedisUntil redisUntil;
}
