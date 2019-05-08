package com.wandou.demo.thread;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: liming
 * @date: 2019/5/8 11:46
 * @description:
 * @modify:
 */
@Component
public class CompletableFeatureDemo {

    @Qualifier("futureTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolExecutor;

    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public CompletableFuture<String> m1() {
        return CompletableFuture.supplyAsync(() -> {
            String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss.S");
            String name = Thread.currentThread().getName();
            return name + format;
        }, threadPoolExecutor);
    }


    public CompletableFuture<Integer> add() {
        return CompletableFuture.supplyAsync(() -> {
            for (int j = 0; j < 100; j++) {
                i++;
            }
            System.out.println("i = " + i + "; 线程: " + Thread.currentThread().getName());
            return 1;
        }, threadPoolExecutor);
    }

    public void addExe() {
        i++;
        System.out.println("i = " + i + "; 线程: " + Thread.currentThread().getName());
    }

//    public CompletableFuture<GoodsDTO> getGoods(DisclosureDTO disclosure) {
//        return CompletableFuture.supplyAsync(() -> {
//            ValueResult<GoodsDTO> goods = disclosureCall.getGoods(disclosure.getGid());
//
//            return goods.getResult();
//        }, executor);
//    }



}
