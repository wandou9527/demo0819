package com.wandou.proxy;

/**
 * @author liming
 * @date 2018/9/2
 * @description 不实现接口
 */
public class TargetCglib {
    public void show1() {
        System.out.println("cglib show1 ...");
    }


    public void show2(String str) {
        System.out.println("cglib show2 ... " + str);
    }


    public String show3(int age) {
        return "cglib show3 ... age: " + age;
    }
}
