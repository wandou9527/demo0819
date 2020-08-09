package com.wandou.demo.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020/8/9 18:26
 * @description
 */
public class ThreadPoolDemoV3 {

    private static LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(30);
    private static MyThreadPoolV3 threadPoolV3 = new MyThreadPoolV3(3, workQueue);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(atomicInteger.incrementAndGet()
                        + "号顾客来理发，为其理发的理发师是："
                        + Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < 30; i++) {
            threadPoolV3.submit(task);
        }
    }
}
