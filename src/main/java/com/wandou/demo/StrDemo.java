package com.wandou.demo;

import org.junit.Test;

public class StrDemo {
    /**
     * @date 2018-8-21
     * https://www.cnblogs.com/deltadeblog/p/7594038.html
     */
    @Test
    public void m1() {
        String s1 = "haha";
        String s2 = "haha";
        System.out.println("s1 == s2 ? " + (s1 == s2));

        //--------------------
        String s3 = new String("aa");
        String s4 = new String("aa");
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));

        String s5 = "HelloJava";
        String s6 = "Hello" + "Java";
        System.out.println(s5 == s6);

        String s7 = "Hello";
        String s8 = "Java";
        String s9 = s7 + s8;
        System.out.println("s5 == s9 ? " + (s5 == s9));//false

        final String s10 = "Hello";
        final String s11 = "Java";
        String s12 = s10 + s11;
        System.out.println("s5 == s12 ? " + (s5 == s12));//true
        //这里s10与s11位由final修饰为宏变量，编译器会把程序中所有用到该变量的地方直接替换成该变量的值，故s12编译时就确定了。
        //指向常量池中的对象。
    }
}
