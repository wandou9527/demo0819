package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wandou.enums.OrderEnum;
import com.wandou.model.Book;
import com.wandou.model.MemberPrivilege;
import org.junit.Test;
import org.springframework.data.redis.connection.convert.LongToBooleanConverter;

import java.math.BigDecimal;

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
        System.out.println("=============");
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

    /**
     * 枚举
     */
    @Test
    public void m4() {
        System.out.println(OrderEnum.ORDER_ENUM);
        System.out.println(OrderEnum.TypeEnum.GROUP);
        System.out.println(OrderEnum.StatusEnum.PAY_SUCCESS);
        System.out.println("字符串啦啦啦");
    }


    @Test
    public void m5() {
        System.out.println(JSON.toJSONString(new MemberPrivilege()));
    }

    @Test
    public void m6() {
        BigDecimal bigDecimal = new BigDecimal(1.05);
        BigDecimal bigDecimal1 = null;
        BigDecimal bigDecimal2 = new BigDecimal(2.1);

        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }

        System.out.println(bigDecimal.add(bigDecimal1).add(bigDecimal2).setScale(2, BigDecimal.ROUND_CEILING));
    }

    @Test
    public void m7() {
        System.out.println(1000129142 % 32);
        System.out.println(1000108736 % 32);
        System.out.println(1800002936 % 32);
    }

    /**
     * BigDecimal
     */
    @Test
    public void m8BigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(0.5);
        BigDecimal bigDecimal1 = new BigDecimal(0.1551);
        bigDecimal = bigDecimal.add(bigDecimal1);
//        bigDecimal = bigDecimal.add(new BigDecimal(0.09));
        System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
