package com.wandou.demo;

/**
 * @author: liming
 * @date: 2019/5/8 17:13
 * @description:
 * @modify:
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance;

    /**
     * 几乎完美的了
     * 2019-05-08
     *
     * @return
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    /**
     * 原始
     *
     * @return
     */
    public static SingletonDemo getInstance2() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    /**
     * 方法同步锁
     *
     * @return
     */
    public synchronized static SingletonDemo getInstance3() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    /**
     * 创建对象时同步锁，问题：进if后和拿锁前，有线程拿到锁并创建成功返回并释放锁，如此就不是单例了
     *
     * @return
     */
    public static SingletonDemo getInstance4() {
        if (instance == null) {
            //[1]
            synchronized (SingletonDemo.class) {
                instance = new SingletonDemo();
            }
        }
        return instance;
    }

}
