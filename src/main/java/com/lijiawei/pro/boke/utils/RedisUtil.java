package com.lijiawei.pro.boke.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    // 困难 1. 返回类型不用做强制类型转换 -- 将原有的Object方法包装为泛型方法
    public static <T> T getCacheObject(String key) {
        ValueOperations<String,T> valueOperations = (ValueOperations<String, T>) redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public static boolean setExpireTime(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key,time,unit);
    }
}
