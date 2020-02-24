package com.wandou.demo.thread;

import com.wandou.util.DateUtil;
import lombok.Getter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: liming
 * @date: 2019/5/8 11:46
 * @description: 返回吗 revert
 * @modify:
 */

@Getter
@Component
public class CompletableFeatureDemo {

    @Qualifier("futureTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolExecutor;

    private int i = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

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


    /**
     * 累加 两个线程操作一个变量累加
     *
     * @return
     */
    public CompletableFuture<Integer> add() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("add CompletableFuture.supplyAsync() 线程 " + Thread.currentThread().getName() + " 开始");
            for (int j = 0; j < 5000; j++) {
                i++;
                atomicInteger.incrementAndGet();
            }
            //一个线程结束时打印变量值 最终值应该等于循环累加次数结果 后停止的线程应该等于最终值，先停止的是那一时刻两个线程操作的结果
            System.out.println("i = " + i + "; 线程: " + Thread.currentThread().getName() + " 结束");
            System.out.println("atomicInteger = " + atomicInteger + "; 线程： " + Thread.currentThread().getName() + " 结束");
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

    // 2019-7-5 17:27:21

}
