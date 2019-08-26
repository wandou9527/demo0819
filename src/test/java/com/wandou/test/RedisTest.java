package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.wandou.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: liming
 * @date: 2019/8/23 19:12
 * @description:
 * @modify:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


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
     *
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
        System.out.println(bainiangudu);
//        System.out.println(JSON.toJSONString(bainiangudu));

        redisTemplate.opsForValue().set("rk101", "rv101");
        Object k101 = redisTemplate.opsForValue().get("rk101");
        System.out.println(k101);
    }




}