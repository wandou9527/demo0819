package com.wandou.demo.thread.post.threapool;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020/9/17
 * @description
 */
public class MyThreadPoolDemo {

    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    private MyThreadPool myThreadPool = new MyThreadPool(2, workQueue);


    @Test
    public void t1() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
//                    Thread.sleep(1);
                    System.out.println(atomicInteger.incrementAndGet()
                            + "号顾客来理发，为其理发的理发师是："
                            + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 30; i++) {
            myThreadPool.execute(task);
        }
        Thread.sleep(5000);
        System.out.println("-----------------------------");
        for (int i = 0; i < 30; i++) {
            myThreadPool.execute(task);
        }
        //让主线程阻塞等待
        System.in.read();
    }

}
