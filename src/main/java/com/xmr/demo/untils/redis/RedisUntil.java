package com.xmr.demo.untils.redis;

import java.util.Map;

public interface RedisUntil {

    Boolean hasKey(String key);
    Object get(String key);
    Map getLogin(String key);
    void set(String key, Object value);
    void set(String key, Object value, long offset);
    void delete(String key);
    String setLogin(String key, String value);
}
