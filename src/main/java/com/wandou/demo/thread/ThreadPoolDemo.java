package com.wandou.demo.thread;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020/7/28
 * 研究一下线程池里的线程是怎么消亡的
 */
public class ThreadPoolDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque(200);
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,
            10,
            DateUtils.MILLIS_PER_DAY,
            TimeUnit.MILLISECONDS,
            blockingQueue,
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "t-" + atomicInteger.incrementAndGet());
                }
            });


    @Test
    public void t1() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("现在时间：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        "；当前线程：" + Thread.currentThread().getName() +
                        "；队列长度：" + threadPool.getQueue().size());
            }
        };
        for (int i = 0; i < 50; i++) {
            threadPool.execute(runnable);
        }
        TimeUnit.SECONDS.sleep(1);
    }


}
