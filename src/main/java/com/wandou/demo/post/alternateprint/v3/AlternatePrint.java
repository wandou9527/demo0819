package com.wandou.demo.post.alternateprint.v3;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
    public void alternatePrint() throws InterruptedException, IOException {
        alphabetBlockingQueue.add(alphabets[0]);

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
                    try {
                        System.out.println(numBlockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i < alphabets.length - 1) {
                        alphabetBlockingQueue.add(alphabets[i + 1]);
                    }
                }
            }
        });

        t1.start();
        t2.start();

        // 主线程阻塞等待子线程运行完毕
        System.in.read();
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
}
