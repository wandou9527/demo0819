package com.wandou.util;

import com.wandou.common.RedisConstant;
import org.apache.commons.lang3.RandomUtils;
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


    public Long randomIncId() {
        Long increment = redisTemplate.opsForValue().increment(RedisConstant.RANDOM_INC_ID_KEY,
                RandomUtils.nextLong(1L, 4L));
        return increment;
    }


}
