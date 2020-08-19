package com.wandou.demo.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020-08-01
 * @description
 */
public class MyThreadPoolDemo {

    private MyThreadPoolV2 myThreadPoolV2 = new MyThreadPoolV2(3, 200);

    @Test
    public void t1() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("我提交的任务执行: " + atomicInteger.getAndIncrement() + "，当前线程：" + Thread.currentThread().getName());
            }
        };
        myThreadPoolV2.submit(runnable);
        myThreadPoolV2.submit(runnable);
        myThreadPoolV2.submit(runnable);
        myThreadPoolV2.submit(runnable);
        for (int i = 0; i < 200; i++) {
            boolean submit = myThreadPoolV2.submit(runnable);
        }

        TimeUnit.SECONDS.sleep(5);
    }
}
