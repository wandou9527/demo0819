package com.wandou.demo;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020-11-30
 * @description
 */
public class AtomicDemo {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void m1() {
        /**
         * 内部用到 Unsafe
         */
        int i = atomicInteger.incrementAndGet();
        System.out.println("i = " + i);
        i = atomicInteger.incrementAndGet();
        System.out.println("i = " + i);
    }
}
