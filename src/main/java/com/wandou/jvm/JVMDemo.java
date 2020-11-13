package com.wandou.jvm;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liming
 * @date 2020-05-26
 * @description
 */
public class JVMDemo {

    public static void main(String[] args) throws InterruptedException {
        List<JVMDemo> list = new ArrayList<>();
        int i = 0;
        while (true) {
//            System.out.println(i++);
            list.add(new JVMDemo());
            Thread.sleep(1L);
        }
    }

    @Test
    public void m1() {
        File javaIoTmpDir = SystemUtils.getJavaIoTmpDir();
        System.out.println(javaIoTmpDir);
        String hostName = SystemUtils.getHostName();
        System.out.println(hostName);

        // VM options: -Drocketmq.namesrvAddr=127.0.0.1:9876
        String property = System.getProperty("rocketmq.namesrvAddr");
        System.out.println(property);
    }

    @Test
    public void m2GC() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add("a" + i++);
            Thread.sleep(100L);
        }
    }
}
