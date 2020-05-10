package com.wandou.demo;

import com.alibaba.fastjson.JSON;
import com.wandou.enums.AEnum;
import com.wandou.enums.AlipayTradeStatusEnum;
import com.wandou.model.BankOrderDTO;
import com.wandou.model.Book;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.GenericServlet;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liming
 * @date 2018/9/21 15:54
 * @description
 * @modify
 */

public class Demo {


//    public static void main(String[] args) {
//        boolean b = ifStr("22");
//        System.out.println(b);
//    }

    public static boolean ifStr(String str) {
        return StringUtils.isNotBlank(str);
    }


    /**
     * jia shang 2018年8月19日
     */

    /**
     * https://blog.csdn.net/tiwerbao/article/details/42836305 apache common
     */
    @Test
    public void m3() {
        int[] arr1 = {1, 5, 8};
        int[] arr2 = {5, 8, 1};
        boolean equals = Arrays.equals(arr1, arr2);
        System.out.println(equals);//false

        boolean equals1 = ArrayUtils.isEquals(arr1, arr2);
        System.out.println(equals1);//false


    }

    @Test
    public void m4List() {
        final List<Integer> list = new ArrayList();
        list.add(6);
        list.add(9);
        list.add(77);

        for (Integer i : list) {
            System.out.println(i);
        }

//        list.stream().forEach( i () -> { System.out.println(i)});

        Class<GenericServlet> genericServletClass = GenericServlet.class;
    }

    @Test
    public void m5E() {
        String name = AEnum.A.name();//名字内部原生的的名字
        int ordinal = AEnum.A.ordinal();//内部自动排序的序号
        System.out.println(AEnum.A);
        System.out.println(name);
        System.out.println(ordinal);
        System.out.println(AEnum.A.getCode());
        System.out.println(AEnum.A.getDescription());
        System.out.println(AEnum.getByCode(0));
        System.out.println(AEnum.getByCode(2));
        System.out.println(AEnum.getByCode(3));
        System.out.println("#################");
    }

    @Test
    public void m6BEnum() {
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED);
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED.getDescription());
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED.getDeclaringClass());

    }

    //测验的基础，公共的（下面有：a 分支 看能否保留；）
    //a 分支 看能否保留

    /**
     * 延时执行线程池 定时任务
     */
    @Test
    public void m7() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延时线程池执行");
            }
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延时线程池执行2");
            }
        }, 5, 5, TimeUnit.SECONDS);

        Thread.sleep(60000L);
    }

    public void m8() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.opsForValue().setIfAbsent(null, null);
    }

    /**
     * char 的默认值？ '\u0000'
     */
    @Test
    public void m9Char() {
        char minValue = Character.MIN_VALUE;
        System.out.println("--- " + minValue);
        System.out.println("--- " + (int) minValue);
        System.out.println("--- " + Character.MAX_VALUE);
        System.out.println("--- " + (int) Character.MAX_VALUE);
    }

    @Test
    public void m10() {
        String pre = "alter table account_";
//        String suffix = " add un_due_point bigint default 0 not null comment '未过期积分';";
        String suffix = " modify un_due_point bigint default 0 not null comment '未过期积分' after authorize_point;";
        for (int i = 0; i < 32; i++) {
            System.out.println(pre + i + suffix);
        }
    }


    @Test
    public void m11() {
        Book book = JSON.parseObject(null, Book.class);
        System.out.println(JSON.toJSONString(book));

        System.out.println(JSON.toJSONString(JSON.parseObject("", Book.class)));

        BankOrderDTO bankOrderDTO = JSON.parseObject("{\n" +
                "\t\"bankAssetsNo\": \"201910181105122592416013298\",\n" +
                "\t\"bankCode\": \"JC\",\n" +
                "\t\"bankName\": \"金城银行\",\n" +
                "\t\"channelCode\": \"1\",\n" +
                "\t\"channelUserId\": \"1000102242\",\n" +
                "\t\"expireTime\": \"\",\n" +
                "\t\"holdingAmount\": \"1000.00\",\n" +
                "\t\"id\": 4054,\n" +
                "\t\"idCardNo\": \"612729198806211820\",\n" +
                "\t\"interestTime\": \"\",\n" +
                "\t\"orderNo\": \"201910181105122592416013298\",\n" +
                "\t\"orderStatus\": \"2\",\n" +
                "\t\"periodDesc\": null,\n" +
                "\t\"productName\": \"金慧存04期\",\n" +
                "\t\"profit\": \"4.25\",\n" +
                "\t\"spendAmount\": \"1000.00\",\n" +
                "\t\"spendTime\": \"2019-10-18 11:05:12\",\n" +
                "\t\"productId\": 254\n" +
                "}", BankOrderDTO.class);
        System.out.println(bankOrderDTO);
        System.out.println(JSON.toJSONString(bankOrderDTO));


    }

    /**
     * Java 常用常量
     * Charsets UTF-8
     * http://blog.sina.com.cn/s/blog_3fba24680102xlxc.html Java中HTTPStatus-Code常量、Charset常量
     */
    @Test
    public void m12() {
        System.out.println("StandardCharsets.UTF_8 = " + StandardCharsets.UTF_8);
        System.out.println("StandardCharsets.UTF_8.toString() = " + StandardCharsets.UTF_8.toString());
        System.out.println("StandardCharsets.UTF_8.aliases() = " + StandardCharsets.UTF_8.aliases());
        System.out.println("StandardCharsets.UTF_8.name() = " + StandardCharsets.UTF_8.name());

        String ctt = ZoneId.SHORT_IDS.get("CTT");
        System.out.println("ctt = " + ctt);

        System.out.println("HTTP.UTF_8 = " + HTTP.UTF_8);
    }

    static class HashMapDemo {

        public static void main(String[] args) {

            LockSupport.unpark(Thread.currentThread());

            LockSupport.park();

            System.out.println("hello");

            LockSupport.unpark(Thread.currentThread());

            LockSupport.unpark(Thread.currentThread());

            LockSupport.park();

            LockSupport.park(); //到此处阻塞 wait

            System.out.println("hello");

        }

    }

    @Test
    public void m4() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void lanya() {
        for (int i = 0; i < 10; i++) {
            System.out.println("蓝牙 " + i);
            System.out.println("蓝牙 ");
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);
            System.out.println("蓝牙 Mac" + i);

        }
    }

    @Test
    public void m9() throws InterruptedException {
        int x = 4;
        System.out.println("v is: " + ((x > 4) ? 99.9 : 9));

        Object o = new Object();
        o.wait();

        float f = 1.1f;

        Object oa = new float[30];

    }

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                pong();
            }
        };
        //实际是主线程调了run方法
        t.run();
        //新建的线程执行run方法
        t.start();
        System.out.println("ping");
    }

    static void pong() {
        System.out.println("pong, Thread: " + Thread.currentThread().getName());
    }

}
