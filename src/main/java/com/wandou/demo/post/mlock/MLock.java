package com.wandou.demo.post.mlock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liming
 * @date 2020-09-24
 * @description 手写java锁
 * 目前只实现了lock、unlock方法
 */
public class MLock implements Lock {

    /**
     * AtomicReference包装的变量对其修改时原子性的，不会产生并发问题，内部用CAS实现
     */
    private AtomicReference<Thread> owner = new AtomicReference<>();
    /**
     * 阻塞队列是线程安全的，内部用 ReentrantLock 保证线程安全
     */
    private BlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    @Override
    public void lock() {
        //用while循环是因为解锁unpark后不能直接运行，要重新抢锁，方可保证锁的互斥性
        while (!owner.compareAndSet(null, Thread.currentThread())) {
            try {
                waiters.put(Thread.currentThread());
            } catch (InterruptedException e) {
                //真实生产中不要用此方式处理异常，应使用log记日志
                e.printStackTrace();
            }
            LockSupport.park();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            while (true) {
                Thread next = waiters.poll();
                if (next == null) {
                    break;
                }
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
