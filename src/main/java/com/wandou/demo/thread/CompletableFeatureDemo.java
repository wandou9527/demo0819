package com.wandou.demo.thread;

import com.wandou.util.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

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
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
    }

    /**
     * 日期
     *
     * @return
     */
    public CompletableFuture<String> m1() {
        return CompletableFuture.supplyAsync(() -> {
            String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss.S");
            String name = Thread.currentThread().getName();
            System.out.println(name + format + " CF里");
            return name + format;
        }, threadPoolExecutor);
    }


    public CompletableFuture<Integer> add() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("add CompletableFuture.supplyAsync() 线程 " + Thread.currentThread().getName() + " 开始");
            for (int j = 0; j < 5000; j++) {
                i++;
            }
            System.out.println("i = " + i + "; 线程: " + Thread.currentThread().getName() + " 结束");
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

    /**
     * 模拟dateformat并发问题
     *
     * @param countDownLatch
     */
    public void dateFormat(CountDownLatch countDownLatch) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 就绪 ...");
            System.out.println("线程池 ActiveCount：" + threadPoolExecutor.getActiveCount());
            try {
                for (int j = 0; j < 10; j++) {
                    String format = DateUtil.format(new Date());
                    System.out.println(format + " " + Thread.currentThread().getName() + "运行 。。。");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            return null;
        }, threadPoolExecutor);
    }


}
