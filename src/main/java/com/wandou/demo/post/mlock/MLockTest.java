package com.wandou.demo.post.mlock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author liming
 * @date 2020/9/24
 * @description
 */
public class MLockTest {

    private int count = 0;
    private int countWithoutLock = 0;

    /**
     * 多线程累加 测试锁的效果
     */
    @Test
    public void multiThreadAdd() throws InterruptedException {
        int threadNum = 5;
        int circulationNum = 1000;
        Lock lock = new MLock();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    for (int j = 0; j < circulationNum; j++) {
                        count++;
                    }
                    lock.unlock();
                }
            });
            thread.start();
        }
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < circulationNum; j++) {
                        countWithoutLock++;
                    }
                }
            });
            thread.start();
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println("count = " + count);
        System.out.println("countWithoutLock = " + countWithoutLock);
    }

}
