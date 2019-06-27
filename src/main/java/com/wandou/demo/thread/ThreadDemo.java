package com.wandou.demo.thread;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liming
 * @date: 2019/5/8 16:30
 * @description:
 * @modify:
 */
public class ThreadDemo {

    private static int anInt = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque();
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,
            10, DateUtils.MILLIS_PER_DAY, TimeUnit.MILLISECONDS, blockingQueue);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
//            m2Pool();
//            m1a();
            m3PoolSubmit();
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
                synchronized (ThreadDemo.class) {
                    anInt++;
                }
                atomicInteger.incrementAndGet();
            }
        });
        thread.start();
    }

    /**
     * threadPool 方式
     */
    public static void m2Pool() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    anInt++;
                    atomicInteger.incrementAndGet();
                }
            }
        });
        System.out.println("m2Pool方法被调用");
    }


    public static void m3PoolSubmit() {
        Future<?> future = threadPool.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    anInt++;
                    atomicInteger.incrementAndGet();
                }
            }
        });
    }

    /**
     * 锁
     */
    public void lockDemo() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
    }

    /**
     * 多谢程同步器
     * 提交runnable，以多线程方式执行
     *  @param threadNum      线程数
     * @param circulationNum 循环次数
     * @param runnable
     * @param countDownLatch
     */
    public void threadToolMethod(int threadNum, int circulationNum, Runnable runnable, CountDownLatch countDownLatch) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
//        countDownLatch.await();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            System.out.println("线程 " + thread.getName() + " 创建完毕 ");
            countDownLatch.countDown();
            System.out.println("countDownLatch - 1");
//            countDownLatch.await();
//            System.out.println("线程 " + thread.getName() + " start 。。。 ");
        }

    }

}
