package com.xmr.demo.untils.redis.impl;


import com.xmr.demo.untils.redis.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Objects;


@Component
public class RedisUntilImpl implements RedisUntil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 是否存在键为key的某条数据
     *
     * @param key 键值名称
     * @return true表示存在，false表示不存在
     */
    @Override
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键值名称
     * @return
     */
    @Override
    public Object get(String key){
        if (key == null || !redisTemplate.hasKey(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置永久有效的缓存
     *
     * @param key   键值名称
     * @param value 键值
     */
    @Override
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置带时间的缓存
     *
     * @param key    键值名称
     * @param value  键值
     * @param offset 失效时间 要大于0 如果time小于等于0 将设置无限期
     */
    @Override
    public void set(String key, Object value, long offset){
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 删除键为key的数据
     *
     * @param key 键值名称
     */
    @Override
    public void delete(String key){
        if (key != null && redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public String setLogin(String key, Object value, long offset) {
        String data = base64Encode(key);
        redisTemplate.opsForValue().set(data, value, offset);
        return data;
    }

    @Override
    public String getLogin(String key) {
        if (key == null || !redisTemplate.hasKey(key)) {
            return null;
        }
        System.out.println(redisTemplate.opsForValue().get(key));
        return base64Decode(Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString());
    }


    public String base64Encode(String data){
        return Base64.getEncoder().encodeToString(data .getBytes());
    }

    public String base64Decode(String data){
        return new String(Base64.getDecoder().decode(data));
    }
}
