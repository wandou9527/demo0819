package com.wandou.demo.thread;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.*;
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

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

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
     * 多线程同步器 不太好用
     * 提交runnable，以多线程方式执行
     *
     * @param threadNum      线程数
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

    @Test
    public void m4() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run method ... ");
            }
        };
        runnable.run();
        Executors.newCachedThreadPool();

    }

    /**
     * CountDownLatch
     * 好用 2019-07-03
     */
    @Test
    public void m5() throws InterruptedException {
        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    System.out.println("线程 " + Thread.currentThread().getName() + " 准备");
                    countDownLatch.countDown(); // 放这里可以，在await前面
                    countDownLatch.await();
                    System.out.println("线程 " + Thread.currentThread().getName() + " 运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    countDownLatch.countDown(); // 这里也无法统一运行
                }
            }).start();
            // countDownLatch.countDown(); 如放此处不行，无法统一运行，可能因为循环一次线程对象并没有创建完
        }
//        Thread.sleep(3000L);
//        countDownLatch.countDown();
        Thread.sleep(3000L);
    }

    public void m5a(int threadNum, int circulationNum, Runnable runnable) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    System.out.println("线程 " + Thread.currentThread().getName() + " 准备");
                    countDownLatch.countDown(); // 放这里可以，在await前面
                    countDownLatch.await();
                    System.out.println("线程 " + Thread.currentThread().getName() + " 运行");
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(3000L);
    }

    @Test
    public void m5bTest() throws InterruptedException {
        int threadNum = 1000;
        List list = new ArrayList();
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue(threadNum);
        final int[] arr = {0};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                queue.add(new Random().nextInt());
                list.add(new Random().nextInt());
                arr[0]++;
            }
        };
        m5a(threadNum, 0, runnable);
        System.out.println(queue.size());
        System.out.println(queue);
        System.out.println("list size: " + list.size());
        System.out.println("list: " + list);
        System.out.println("累加结果 " + arr[0]);
    }

}
