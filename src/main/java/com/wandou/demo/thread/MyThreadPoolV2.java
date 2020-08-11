package com.wandou.demo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liming
 * @date 2020-08-05
 * @description 简化版
 */

public class MyThreadPoolV2 {

    private Set<Worker> workers = new HashSet<>();
    private int corePoolSize;
    private BlockingQueue<Runnable> workQueue;

    public MyThreadPoolV2(int corePoolSize, int workQueueSize) {
        this.corePoolSize = corePoolSize;
        this.workQueue = new LinkedBlockingQueue<>(workQueueSize);
        for (int i = 0; i < corePoolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public boolean submit(Runnable runnable) {
        return workQueue.offer(runnable);
    }

    final void runWorker(Worker worker) {
        // 执行队列中的任务
        System.out.println("执行队列中的任务");
        while (true) {
            Runnable task = null;
            try {
                task = workQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (task != null) {
                task.run();
            }
        }

    }


    // ---------------------------------------------------

    public class Worker extends Thread {

        public Worker() {
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

}

