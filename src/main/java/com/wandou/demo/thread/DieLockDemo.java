package com.wandou.demo.thread;

/**
 * @author liming
 * @date 2020/11/5
 * @description
 */
public class DieLockDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        new Thread(new MyRunnable(obj1, obj2)).start();
        new Thread(new MyRunnable(obj2, obj1)).start();

    }

    public static class MyRunnable implements Runnable {
        private final Object lockA;
        private final Object lockB;

        public MyRunnable(Object lockA, Object lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "拿到lockA");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "拿到lockB");
                }
            }
        }
    }

}
