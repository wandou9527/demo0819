package com.wandou.test;

import com.wandou.controller.IndexController;
import com.wandou.demo.thread.CompletableFeatureDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author: liming
 * @date: 2019/5/8 11:51
 * @description:
 * @modify:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ATest {

    @Autowired
    private IndexController indexController;
    @Autowired
    private CompletableFeatureDemo completableFeatureDemo;

    private int anInt = 0;

    @Test
    public void m1() {
        String index = indexController.index();
        System.out.println(index);
    }

    @Test
    public void m2() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> stringCompletableFuture = completableFeatureDemo.m1();
            String str = stringCompletableFuture.get();
            System.out.println(str);
        }
    }

    /**
     * 多线程累加测试 CompletableFeature 形式
     * 调一次add方法为从线程池拿一个线程执行，线程中循环1k次无并发问题，5k次发生并发问题
     */
    @Test
    public void addTest() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> addCF = completableFeatureDemo.add();
        CompletableFuture<Integer> addCF2 = completableFeatureDemo.add();
//        CompletableFuture<Integer> addCF3 = completableFeatureDemo.add();
//        CompletableFuture<Integer> addCF4 = completableFeatureDemo.add();

        CompletableFuture.allOf(addCF, addCF2);
        Thread.sleep(5000);
        System.out.println("CompletableFeatureDemo.i 的最终值： " + completableFeatureDemo.getI());
    }

    @Test
    public void addTest2() throws InterruptedException, ExecutionException {
        completableFeatureDemo.add();
        completableFeatureDemo.add();

        Thread.sleep(5000);
        System.out.println("CompletableFeatureDemo.i 的最终值： " + completableFeatureDemo.getI());
    }

    @Test
    public void testDateFormat() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        completableFeatureDemo.dateFormat(countDownLatch);
        completableFeatureDemo.dateFormat(countDownLatch);
        completableFeatureDemo.dateFormat(countDownLatch);
        completableFeatureDemo.dateFormat(countDownLatch);

        //等待线程执行完 3个
        countDownLatch.await();
        System.out.println("主线程结束");

    }

}
