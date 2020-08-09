package com.wandou.demo.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author liming
 * @date 2020/8/9 17:54
 * @description
 */
public class MyThreadPoolV3 {

    /**
     * 核心线程数（理发师数量）
     */
    private volatile int corePoolSize;
    /**
     * 任务等待队列（等待区座位数）
     */
    private final BlockingQueue<Runnable> workQueue;
    /**
     * 线程容器（理发师作业区）
     */
    private List<Worker> workers;

    public MyThreadPoolV3(int corePoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.workQueue = workQueue;
        workers = Collections.synchronizedList(new ArrayList<>(corePoolSize));
        for (int i = 0; i < corePoolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * 将任务加到队列（给顾客安排位置）
     *
     * @param task
     * @return
     */
    public boolean submit(Runnable task) {
        return workQueue.offer(task);
    }


    // ----------------

    /**
     * 线程（理发师）
     */
    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    //从任务队列取任务，如空则阻塞等待。（去等候区叫顾客，如果没有则原地等待一会）
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    //取到任务开始工作（开始理发）
                    task.run();
                }
            }
        }
    }
}
