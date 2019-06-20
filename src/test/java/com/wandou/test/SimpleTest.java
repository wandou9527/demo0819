package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

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

        Object parse2 = JSONObject.parse("{\"id\":888}");
        JSONObject jsonObject = JSONObject.parseObject("{\"id\":888}");
        System.out.println(jsonObject);

        System.out.println("=============");
        Object obj2 = JSON.parse("{\"id\":889}");
        System.out.println(obj2);
        System.out.println(obj2.getClass());

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.toString();
    }
}
