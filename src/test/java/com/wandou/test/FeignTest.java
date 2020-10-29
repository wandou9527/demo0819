package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.wandou.feign.BaiduFeign;
import com.wandou.feign.MouseFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liming
 * @date 2020/10/29
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTest {

    @Autowired
    private MouseFeign mouseFeign;
    @Autowired
    private BaiduFeign baiduFeign;

    @Test
    public void feignTest() {
        System.out.println("mouseFeign = " + mouseFeign);
        Object c2Paris = mouseFeign.c2Paris();
        System.out.println(JSON.toJSONString(c2Paris));
    }

    @Test
    public void testListMatterLog() {
        Object o = mouseFeign.listMatterLog();
        System.out.println("o = " + o);
    }

    @Test
    public void testBaidu() {
        Object search = baiduFeign.search();
        System.out.println("search = " + search);
    }
}
