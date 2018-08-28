package com.wandou.demo;

import org.junit.Test;

public class IntegerDemo {
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
        System.out.println(Integer.MAX_VALUE);

        System.out.println(9 & 5);
    }
}
