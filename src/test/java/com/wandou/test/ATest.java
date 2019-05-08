package com.wandou.test;

import com.wandou.controller.IndexController;
import com.wandou.demo.thread.CompletableFeatureDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
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
     * 多线程累加测试
     */
    @Test
    public void addTest() throws InterruptedException {
        for (int i=0; i > 10; i++) {
            completableFeatureDemo.add();
        }
        Thread.sleep(9000);
        System.out.println("i 的最终值： " + completableFeatureDemo.getI());
    }

}
