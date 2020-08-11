package com.wandou.demo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liming
 * @date 2020-08-01
 * @description 尽量模仿 jdk
 */
public class MyThreadPool {

    private Set<Worker> workers = new HashSet<>();
    private int corePoolSize = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);

    public void submit(Runnable runnable) {
        if (workers.size() < corePoolSize) {
            addWorker(runnable);
        } else {
            workQueue.offer(runnable);
            System.out.println("workQueue = " + workQueue);
        }
    }

    private boolean addWorker(Runnable firstTask) {
        Worker worker = new Worker(firstTask);
        workers.add(worker);
        worker.thread.start();
        return true;
    }

    final void runWorker(Worker worker) {
        if (worker.firstTask != null) {
            worker.firstTask.run();
        }
        // 执行队列中的任务
        System.out.println("执行队列中的任务");
        while (true) {
            Runnable task = null;
            try {
                task = workQueue.take();
                if (task != null) {
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (task != null) {
                task.run();
            }
        }

    }


    // ---------------------------------------------------

    public class Worker implements Runnable {

        Runnable firstTask;

        final Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = new Thread(firstTask);
        }

        @Override
        public void run() {
            runWorker(this);
        }


    }

}
