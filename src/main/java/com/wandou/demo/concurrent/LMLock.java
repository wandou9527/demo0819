package com.wandou.demo.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liming
 * @date 2020-05-07
 * @description
 */
public class LMLock implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName() + " 来抢锁 lai");
        //用while循环是因为解锁unpark后不能直接运行，要重新抢锁，方可保证锁的互斥性
        while (!owner.compareAndSet(null, Thread.currentThread())) {
            waiters.add(Thread.currentThread());
            LockSupport.park();
        }
        System.out.println(Thread.currentThread().getName() + " 抢到锁了 ok");
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
            System.out.println(Thread.currentThread().getName() + " 释放锁 free");
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
