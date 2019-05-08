package com.wandou.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: liming
 * @date: 2019/5/8 16:30
 * @description:
 * @modify:
 */
public class ThreadDemo {

    private static int anInt = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            m1();
//            m1a();
        }
        Thread.sleep(10000L);
        System.out.println(anInt);
        System.out.println(atomicInteger);
    }

    /**
     * 线程内循环累加
     */
    public static void m1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    anInt++;
                    atomicInteger.incrementAndGet();
                }
            }
        });
        thread.start();
    }

    /**
     * 线程内循环累加 lmd表达式
     */
    public static void m1a() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized(ThreadDemo.class) {
                    anInt++;
                }
                atomicInteger.incrementAndGet();
            }
        });
        thread.start();
    }
}
