package com.wandou.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Random;

/**
 * Redis分布式锁
 * 使用 SET resource-name anystring NX EX max-lock-time 实现
 * <p>
 * 该方案在 Redis 官方 SET 命令页有详细介绍。
 * http://doc.redisfans.com/string/set.html
 * <p>
 * 在介绍该分布式锁设计之前，我们先来看一下在从 Redis 2.6.12 开始 SET 提供的新特性，
 * 命令 SET key value [EX seconds] [PX milliseconds] [NX|XX]，其中：
 * <p>
 * EX seconds — 以秒为单位设置 key 的过期时间；
 * PX milliseconds — 以毫秒为单位设置 key 的过期时间；
 * NX — 将key 的值设为value ，当且仅当key 不存在，等效于 SETNX。
 * XX — 将key 的值设为value ，当且仅当key 存在，等效于 SETEX。
 * <p>
 * 命令 SET resource-name anystring NX EX max-lock-time 是一种在 Redis 中实现锁的简单方法。
 * <p>
 * 客户端执行以上的命令：
 * <p>
 * 如果服务器返回 OK ，那么这个客户端获得锁。
 * 如果服务器返回 NIL ，那么客户端获取锁失败，可以在稍后再重试。
 *
 * @author yuhao.wangwang
 * @version 1.0
 * @date 2017年11月3日 上午10:21:27
 */

@Slf4j
@Component
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String LOCK_KEY_PRE = "Redis_Lock";

    private int i = 0;


    /**
     * 解锁的lua脚本
     */
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    final Random random = new Random();

    public RedisLock() {
    }


    /**
     * @param millis 毫秒
     * @param nanos  纳秒
     * @Title: seleep
     * @Description: 线程等待时间
     * @author yuhao.wang
     */
    private void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            logger.info("获取分布式锁休眠被中断：", e);
        }
    }

    public boolean tryLock(String key, String value, long expireSeconds) {
        final String lockKey = new StringBuilder(LOCK_KEY_PRE).append(key).toString();
        Boolean setBool = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> stringRedisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                byte[] keyByte = stringRedisSerializer.serialize(lockKey);
                //springboot 2.0以上的spring-data-redis 包默认使用 lettuce连接包
                RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
                byte[] valueBytes = valueSerializer.serialize(value);
                log.info("redis setnx: {}", keyByte);
                Boolean setBool = connection.set(keyByte, valueBytes, Expiration.seconds(expireSeconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
                log.info("redis setnx 返回: {}", setBool);
                return setBool;
            }
        });
        if (setBool == null) {
            return false;
        }
        return setBool;
    }

    public void lock(String key, String value, long expireSeconds) {
        final String lockKey = new StringBuilder(LOCK_KEY_PRE).append(key).toString();
        while (true) {
            log.info("循环: {}", i++);
            Boolean setBool = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> stringRedisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                    byte[] keyByte = stringRedisSerializer.serialize(lockKey);
                    //springboot 2.0以上的spring-data-redis 包默认使用 lettuce连接包
                    RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
                    byte[] valueBytes = valueSerializer.serialize(value);
                    Boolean setBool = connection.set(keyByte, valueBytes, Expiration.seconds(expireSeconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
                    return setBool;
                }
            });
            if (setBool) {
                log.info("抢到锁: {}");
                return;
            }
        }

    }

    /**
     * 解锁
     *
     * @param lockKey
     * @param lockValue
     * @return
     */
    public Boolean unlock(String lockKey, String lockValue) {
        lockKey = new StringBuilder(LOCK_KEY_PRE).append(lockKey).toString();
        //注意是Long类型,而不是Integer
        RedisScript redisScript = RedisScript.of(UNLOCK_LUA, Long.class);
        Object exeResult = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), lockValue);
        if (exeResult == null) {
            return false;
        }
        boolean unlockRes = NumberUtils.LONG_ONE.equals(exeResult);
        if (unlockRes) {
            log.info("解锁成功");
        }
        return unlockRes;
    }


}