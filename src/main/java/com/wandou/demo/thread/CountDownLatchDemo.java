package com.wandou.demo.thread;

/**
 * @author: liming
 * @date: 2019/7/3 17:05
 * @description:
 * @modify:
 */

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo {

    private static CountDownLatch cd = new CountDownLatch(100);

    private static final int CONCURRENCE_COUNT = 100 + 1;


    public static void main(String[] args) {
        // 一千个线程，同时怼一个方法
        for (int i = 0; i < CONCURRENCE_COUNT; i++) {
            new Thread(new SendTask()).start();
            cd.countDown();

        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            cd.countDown();
            cd.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("当前用时：" + (System.currentTimeMillis() - currentTimeMillis));
    }


    private static class SendTask implements Runnable {
        @Override
        public void run() {
            try {
                cd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendsms();
        }
    }


    private static void sendsms() {
        System.out.println("信息发送成功" + System.currentTimeMillis());
    }


}
