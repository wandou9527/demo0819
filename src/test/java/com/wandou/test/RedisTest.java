package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.wandou.model.Book;
import com.wandou.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;

/**
 * @author: liming
 * @date: 2019/8/23 19:12
 * @description:
 * @modify:
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Qualifier("myRedisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisLock redisLock;

    private int a = 0;


    /**
     * stringRedisTemplate
     */
    @Test
    public void m1() {
        stringRedisTemplate.opsForValue().set("kboot", "vboot");
        String kbbot = stringRedisTemplate.opsForValue().get("kboot");
        System.out.println(kbbot);

        Book book = new Book();
        book.setPrice(1L);
        book.setName("水浒传");

        stringRedisTemplate.opsForValue().set("sk102", "sv102");
        Object sk102 = stringRedisTemplate.opsForValue().get("sk102");
        System.out.println(sk102.getClass());
        System.out.println(sk102);
        System.out.println("sv102".equals(sk102));


        Object rk101 = stringRedisTemplate.opsForValue().get("rk101");
        System.out.println(rk101.getClass());
        System.out.println(rk101);
        System.out.println("\"rv101\"".equals(rk101));
    }


    /**
     * redisTemplate
     */
    @Test
    public void m2() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        redisTemplate.opsForValue().set("K2", "redisTemplateV");
        Object k2 = redisTemplate.opsForValue().get("K2");
        System.out.println(k2);

        Book book = new Book();
        book.setPrice(2L);
        book.setName("百年孤独");
        redisTemplate.opsForValue().set("bainiangudu", book);
        Object bainiangudu = redisTemplate.opsForValue().get("bainiangudu");
//        System.out.println(bainiangudu);
        System.out.println("Object bainiangudu = " + bainiangudu);
        System.out.println("Object bainiangudu class = " + bainiangudu.getClass());
//        System.out.println(JSON.toJSONString(bainiangudu));

        redisTemplate.opsForValue().set("rk101", "rv101");
        Object k101 = redisTemplate.opsForValue().get("rk101");
        System.out.println("k101: " + k101);
        System.out.println("k101 class: " + k101.getClass());
    }

    /**
     * 2020-01-17 累加 Redis锁 好使可以控制并发
     *
     * @throws InterruptedException
     */
    @Test
    public void addWithRedisLock() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!redisLock.tryLock("a", "abc123", 10000)) {
                        log.info("循环抢锁");
                    }
                    for (int j = 0; j < 10; j++) {
//                        System.out.println(a++);
                        log.info("a++ = {}", a++);
                    }
                    redisLock.unlock("a", "abc123");
                }
            });
            thread.start();
        }
        Thread.sleep(20000);
        System.out.println("a = " + a);
    }


    /**
     * set test
     */
    @Test
    public void m4() {
        String key = "demoSet4";
        Long addRes = redisTemplate.opsForSet().add(key, "1");
        System.out.println("addRes = " + addRes);
        redisTemplate.expireAt(key, DateUtils.addDays(new Date(), 1));
        Long addRes1 = redisTemplate.opsForSet().add(key, "1");
        System.out.println("addRes1 = " + addRes1);
        Long addRes2 = redisTemplate.opsForSet().add(key, "2");
        System.out.println("addRes2 = " + addRes2);
        Long addRes3 = redisTemplate.opsForSet().add(key, "3");
        System.out.println("addRes3 = " + addRes3);
        Long setRes4 = redisTemplate.opsForSet().add(key, "4");
        System.out.println("setRes4 = " + setRes4);
        Set demoSet = redisTemplate.opsForSet().members(key);
        log.info("set: {}", demoSet);
        for (Object o : demoSet) {
            System.out.println(o.getClass());
        }


        // ---------- int -------------
        String keyInt = "demoSetInt4";
        Long addResInt = redisTemplate.opsForSet().add(keyInt, 1);
        System.out.println("addResInt = " + addResInt);
        redisTemplate.expireAt(keyInt, DateUtils.addDays(new Date(), 1));
        Long addResInt1 = redisTemplate.opsForSet().add(keyInt, 1);
        System.out.println("addResInt1 = " + addResInt1);
        Long addResInt2 = redisTemplate.opsForSet().add(keyInt, 2);
        System.out.println("addResInt2 = " + addResInt2);
        Long addResInt3 = redisTemplate.opsForSet().add(keyInt, 3);
        System.out.println("addResInt3 = " + addResInt3);
        Long addResInt4 = redisTemplate.opsForSet().add(keyInt, 4, 5);
        System.out.println("addResInt4 = " + addResInt4);
        Set demoSetInt = redisTemplate.opsForSet().members(keyInt);
        log.info("set int: {}", demoSetInt);
        demoSetInt.stream().forEach(i -> {
            System.out.println("i class = " + i.getClass());
        });

//        ------------ StringRedisTemplate ----------------
        String keyStringRedisTemplate = "demoSetStringRedisTemplate";
        stringRedisTemplate.opsForSet().add(keyStringRedisTemplate, "1");
        stringRedisTemplate.opsForSet().add(keyStringRedisTemplate, "2");
        stringRedisTemplate.opsForSet().add(keyStringRedisTemplate, "3");
        Set<String> members = stringRedisTemplate.opsForSet().members(keyStringRedisTemplate);
        System.out.println("StringRedisTemplate members = " + members);
        members.stream().forEach(o -> {
            System.out.println("o class = " + o.getClass());
        });

    }

    /**
     * increment test
     */
    @Test
    public void m5() {
        String incKey = "inc7";
//        redisTemplate.opsForValue().set(incKey, Integer.MAX_VALUE - 7);
        Long increment = redisTemplate.opsForValue().increment(incKey, 2);
        System.out.println("increment = " + increment);
        Object incValue = redisTemplate.opsForValue().get(incKey);
        System.out.println("incValue = " + incValue);
        System.out.println("incValue class = " + incValue.getClass());


        // -------------------------
        Long incs = stringRedisTemplate.opsForValue().increment("incs", 1);
        System.out.println("incs = " + incs);
        String incsValue = stringRedisTemplate.opsForValue().get("incs");
        System.out.println("incsValue = " + incsValue);
        System.out.println("incsValue class = " + incsValue.getClass());
        Object incsValueR = redisTemplate.opsForValue().get("incs");
        System.out.println("incsValueR = " + incsValueR);
        System.out.println("incsValueR class = " + incsValueR.getClass());

    }

    /**
     * zSet
     */
    @Test
    public void m6ZSet() {
        String zSetKey1 = "zsetKey1";
        redisTemplate.opsForZSet().add(zSetKey1, "zValue1", 10);
        redisTemplate.opsForZSet().add(zSetKey1, "zValue1-a", 1);
        redisTemplate.opsForZSet().add(zSetKey1, "zValue1-b", 5);
        redisTemplate.opsForZSet().add(zSetKey1, "zValue1-c", 3);
        redisTemplate.opsForZSet().add(zSetKey1, "zValue1-d", 4);
        // score为降序排列
        Set set = redisTemplate.opsForZSet().range(zSetKey1, 0L, 2L);
        System.out.println("set = " + set);

        // 倒序查找
        Set<String> reverseRangeSet = redisTemplate.opsForZSet().reverseRange(zSetKey1, 0, 2);
        System.out.println("reverseRangeSet = " + reverseRangeSet);
    }


}
