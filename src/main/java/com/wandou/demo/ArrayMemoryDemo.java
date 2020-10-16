package com.wandou.demo;

import java.io.IOException;

/**
 * @author liming
 * @date 2020/10/15
 * @description
 */
public class ArrayMemoryDemo {

    public static void main(String[] args) throws InterruptedException, IOException {
        Object[] objects = new Object[10];
        Object object = new Object();
        for (int i = 0; i < 10; i++) {
            objects[i] = object;
        }

        Object [] objects1 = new Object[10];
        for (int i = 0; i < 10; i++) {
            objects1[i] = new Object();
        }

        System.in.read();
        Thread.sleep(100000000000L);
    }
}
