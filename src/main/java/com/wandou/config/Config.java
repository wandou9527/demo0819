package com.wandou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author: liming
 * @date: 2019/5/8 11:46
 * @description:
 * @modify:
 */
@Configuration
public class Config {

    /**
     * 异步执行线程池
     *
     * @return
     */
    @Bean(name = "futureTaskExecutor")
    public ThreadPoolTaskExecutor futureTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setCorePoolSize(40);
        executor.setKeepAliveSeconds(900);
        executor.setMaxPoolSize(1000);
        executor.setQueueCapacity(100);
        executor.setAwaitTerminationSeconds(3);
        executor.setRejectedExecutionHandler((r, e) -> {
            new RejectedExecutionException("当前线程已满，请稍后重试");
        });
        return executor;
    }
}
