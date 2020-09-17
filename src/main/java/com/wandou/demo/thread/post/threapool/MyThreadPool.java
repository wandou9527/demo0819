package com.wandou.demo.thread.post.threapool;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020/09
 * @description 自定义线程池
 * new功能
 * - 线程延迟创建（来任务才创建）
 * 欠缺功能：
 * - 队列满了后继续创建线程，直到达到最大线程数
 * - 拒绝策略
 * - 线程超时销毁
 */
public class MyThreadPool implements Executor {

    /**
     * 核心线程数（核心理发师数量）
     */
    private volatile int corePoolSize;
    /**
     * 任务等待队列（等待区座位数）
     */
    private final BlockingQueue<Runnable> workQueue;
    /**
     * 线程容器（理发师作业区）
     */
    private final HashSet<MyThreadPool.Worker> workers = new HashSet<>();

    private final AtomicInteger workerCount = new AtomicInteger(0);


    public MyThreadPool(int corePoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.workQueue = workQueue;
    }

    /**
     * 运行，来顾客了，安排
     *
     * @param command
     * @return
     */
    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }
        int count = workerCount.get();
        // 如果小于核心线程数，可以建立新线程
        if (count < corePoolSize) {
            if (addWorker(command, true)) {
                return;
            }
        }
        if (workQueue.offer(command)) {
            System.out.println("workQueue.size() = " + workQueue.size());
            int recheck = workerCount.get();
            if (recheck == 0) {
                addWorker(null, false);
            }
            return;
        }
        //拒绝
        throw new RejectedExecutionException("Task " + command.toString() +
                " rejected from " +
                this.toString());
    }


    private boolean addWorker(Runnable command, boolean core) {
        for (; ; ) {
            int count = workerCount.get();
            if (count >= corePoolSize) {
                return false;
            }
            // 线程数+1, 成功向下走
            if (workerCount.compareAndSet(count, count + 1)) {
                break;
            }
        }
        Worker worker = new Worker(command);
        final Thread thread = worker.thread;
        workers.add(worker);
        thread.start();
        return true;
    }


    // ----------------

    /**
     * 线程（理发师）
     */
    private class Worker implements Runnable {

        /**
         * 工作者运行的线程
         */
        final Thread thread;
        /**
         * 初始运行的任务，可能为 null
         */
        Runnable firstTask;

        /**
         * 创建一个工作者
         *
         * @param firstTask 第一个任务，可以为 null
         */
        Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            System.out.println("run");
            runWorker(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Worker worker = (Worker) o;
            return Objects.equals(thread, worker.thread) &&
                    Objects.equals(firstTask, worker.firstTask);
        }

        @Override
        public int hashCode() {
            return Objects.hash(thread, firstTask);
        }
    }

    final void runWorker(Worker worker) {
        System.out.println("runWorker");
        Runnable task = worker.firstTask;
        worker.firstTask = null;
        while (task != null || (task = getTask()) != null) {
            System.out.println("runWorker while");
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 运行完毕
                task = null;
            }
        }
    }

    private Runnable getTask() {
        System.out.println("getTask workQueue.size() = " + workQueue.size());
        for (; ; ) {
            try {
                return workQueue.take();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
}
