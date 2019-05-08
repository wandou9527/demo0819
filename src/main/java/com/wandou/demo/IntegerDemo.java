package com.wandou.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerDemo {

    private AtomicInteger atomicInteger = new AtomicInteger(10);

    /**
     * Integer -128~127 会缓存
     * https://www.cnblogs.com/xiehongwei/p/7595520.html
     */
    @Test
    public void m1() {
        Integer i1 = 6;
        Integer i2 = 6;
        System.out.println("Integer 6 == 6 ? " + (i1 == i2));//true

        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println("Integer 129 == 129 ? " + (i3 == i4));//false
        System.out.println("Integer 129 equals 129 ? " + i3.equals(i4));//true
    }

    /**
     * 位运算
     */
    @Test
    public void weiYunSuan() {
        int a = 2 << 4;
        System.out.println(a);

        //Integer.highestOneBit是用来获取最左边的bit（其他bit位为0）所代表的数值.
        int i = Integer.highestOneBit(10);
        System.out.println(i);

        System.out.println(Integer.max(9, 4));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(9 & 5);
    }

    @Test
    public void m3() {
        int min = NumberUtils.min(6, 4, 9);
        System.out.println(min);
        NumberUtils.min(new int[]{2, 4, 9});

        System.out.println(Integer.min(7, 3));
    }

    /**
     * 原子 integer
     */
    @Test
    public void m4() {
        System.out.println(atomicInteger);
        atomicInteger.incrementAndGet();
        boolean b = atomicInteger.compareAndSet(1, 2);
    }
}
