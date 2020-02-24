package com.wandou.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MyZkUtil {

    private static final String connect = "a2.wandour.top:2181";
    private static CuratorFramework curatorFramework;

    private MyZkUtil() {
    }

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//重试策略，初始等待1s,重试3次
        //通过工厂获得CuratorFramework
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connect).connectionTimeoutMs(1000).retryPolicy(retryPolicy).build();
        curatorFramework.start();//开启连接
        System.out.println("curatorFramework start :" + connect);
    }

    public static CuratorFramework getZkCli() {
        return curatorFramework;
    }


}
