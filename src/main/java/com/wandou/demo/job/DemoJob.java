package com.wandou.demo.job;

import com.wandou.rocketmq.RocketMQDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 2020-01-08
 */

@Slf4j
@Component
public class DemoJob {

//    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    @Autowired
    private RocketMQDemo rocketMQDemo;


    @Async
    @Scheduled(cron = "5 0/1 * * * ?")
    public void m1() {
        log.info("m1 定时跑");
        rocketMQDemo.sendToDEMO0819_TOPIC(5);
    }

}
