package com.wandou.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liming
 * @date 2020-03-22
 */

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public Long increment(String key, long delta) {
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        return increment;
    }


}
