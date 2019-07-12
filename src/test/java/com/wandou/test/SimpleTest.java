package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wandou.model.Book;
import org.junit.Test;
import org.redisson.RedissonLock;
import org.springframework.data.redis.connection.convert.LongToBooleanConverter;

/**
 * @author: liming
 * @date: 2019/6/20 14:28
 * @description:
 * @modify:
 */
public class SimpleTest {

    @Test
    public void m1() {
        Object obj = JSONObject.parse("{\"id\":888}", 4);
        System.out.println(obj);
        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());

//        Object parse2 = JSONObject.parse("{\"id\":888}");
        JSONObject jsonObject = JSONObject.parseObject("{\"id\":888}");
        System.out.println(jsonObject);

        System.out.println("=============");
        Object obj2 = JSON.parse("{\"id\":889}");
        System.out.println(obj2);
        System.out.println(obj2.getClass());

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.toString();

        Book book = new Book();
        book.setName("天龙八部");
        book.setPrice(668L);
        System.out.println("book: " + JSON.toJSONString(book));
    }

    /**
     * Long 如果为1L返回true null有空指针风险
     */
    @Test
    public void m2() {
        LongToBooleanConverter converter = new LongToBooleanConverter();
        Boolean convert = converter.convert(1L);
        System.out.println(convert);
        Boolean convert1 = converter.convert(2L);
        System.out.println(convert1);
        Boolean convert2 = converter.convert(null);
        System.out.println(convert2);
    }

    @Test
    public void m3() {
        System.out.println(12 % 2);
    }
}
