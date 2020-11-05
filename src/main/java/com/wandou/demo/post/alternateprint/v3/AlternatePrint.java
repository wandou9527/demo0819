package com.wandou.demo.post.alternateprint.v3;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author liming
 * @date 2020/10/16
 * @description
 */
public class AlternatePrint {

    private Thread t1, t2;
    private final BlockingQueue<Character> numBlockingQueue = new ArrayBlockingQueue<>(1);
    private final BlockingQueue<Character> alphabetBlockingQueue = new ArrayBlockingQueue<>(1);
    private final char[] nums = "1234567".toCharArray();
    private final char[] alphabets = "ABCDEFG".toCharArray();

    /**
     * BlockingQueue 实现
     */
    @Test
    public void alternatePrint() throws InterruptedException {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < alphabets.length; i++) {
                    try {
                        // 返回而且删除队列头数据，如队列为空则阻塞等待
                        System.out.println(alphabetBlockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    numBlockingQueue.add(nums[i]);
                }
            }
        });
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < nums.length; i++) {
                    alphabetBlockingQueue.add(alphabets[i]);
                    try {
                        System.out.println(numBlockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        // 主线程等待子线程运行完毕
        Thread.sleep(10000);
    }

    @Test
    public void test() throws IOException, InterruptedException {
        t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < alphabets.length; i++) {
                    System.out.println(alphabetBlockingQueue.take());
                }
            }
        });
        t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < nums.length; i++) {
                    Thread.sleep(1000);
                    alphabetBlockingQueue.put(alphabets[i]);
                }
            }
        });

        t1.start();
        t2.start();

        System.in.read();
        Thread.sleep(20000L);
    }

    /**
     * 同步队列方式实现
     *
     * @see java.util.concurrent.SynchronousQueue
     */
    @Test
    public void alternatePrintV4() throws InterruptedException {
        final char[] nums = "1234567".toCharArray();
        final char[] alphabets = "ABCDEFG".toCharArray();
        SynchronousQueue<Character> numQueue = new SynchronousQueue<>();
        SynchronousQueue<Character> alphabetQueue = new SynchronousQueue<>();

//        alphabetQueue.offer(alphabets[0]);
//        System.out.println("alphabetQueue = " + alphabetQueue);

        //打印字母
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < nums.length; i++) {
                    try {
                        System.out.println(alphabetQueue.take());
                        numQueue.put(nums[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        //打印数字
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < alphabets.length; i++) {
                    try {
                        alphabetQueue.put(alphabets[i]);
                        System.out.println(numQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(10000);
    }

    @Test
    public void test1() throws InterruptedException {
        // 取数据时，需其他线程插入的数据，否则等待
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("add " + i);
                    try {
                        synchronousQueue.put("abc" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("synchronousQueue = " + synchronousQueue);

                }
            }
        }).start();

        int j = 0;
        while (true) {
            System.out.println("while - " + j++);
            Object obj = synchronousQueue.take();
            System.out.println("obj = " + obj);
        }

    }
}
