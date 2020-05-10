package com.wandou.demo.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author liming
 * @date 2020-05-07
 * @description
 */
public class ConcurrentDemo {
    private int anInt = 0;

    @Test
    public void mThreadAdd() throws InterruptedException {
        Lock lock = new LMLock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    for (int j = 0; j < 10000; j++) {
                        anInt++;
                    }
                    lock.unlock();
                }
            });
            thread.start();
        }


        TimeUnit.SECONDS.sleep(10);
        System.out.println("anInt = " + anInt);
    }

}
