package com.wandou.proxy;

/**
 * @author liming
 * @date 2018/8/28 17:12
 * @description
 */
public class Target implements TargetInterface {
    @Override
    public void show1() {
        System.out.println("show1 ...");
    }

    @Override
    public void show2(String str) {
        System.out.println("show2 ... " + str);
    }

    @Override
    public String show3(int age) {
        return "show3 ... age: " + age;
    }
}
