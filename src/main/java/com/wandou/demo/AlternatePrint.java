package com.wandou.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * @author liming
 * @date 2020/9/24
 * @description 交替打印 A1B2C3 ...
 */
public class AlternatePrint {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < aC.length; i++) {
                    System.out.println(aC[i]);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < aI.length; i++) {
                    LockSupport.park();
                    System.out.println(aI[i]);
                    LockSupport.unpark(t1);
                }
            }
        });

        t1.start();
        t2.start();
    }

}
