package com.wandou.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import com.wandou.config.wechat.WxMaWandouiConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author: liming
 * @date: 2019/5/8 11:46
 * @description:
 * @modify:
 */
@Configuration
public class Config {

    @Autowired
    private WxMaWandouiConfigStorage wxMaWandouiConfigStorage;

    /**
     * 异步执行线程池
     *
     * @return
     */
    @Bean(name = "futureTaskExecutor")
    public ThreadPoolTaskExecutor futureTaskExecutor() {
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

    /**
     * 小程序服务注册
     *
     * @return
     */
    @Bean("wandouiMiniAppService")
    public WxMaService wxMaService() {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaWandouiConfigStorage);
        return service;
    }
}
