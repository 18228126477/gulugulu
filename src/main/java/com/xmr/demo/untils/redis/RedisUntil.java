package com.xmr.demo.untils.redis;

public interface RedisUntil {

    Boolean hasKey(String key);
    Object get(String key);
    void set(String key, Object value);
    void set(String key, Object value, long offset);
    void delete(String key);
    String setLogin(String key, Object value, long offset);
    String getLogin(String key);
}
