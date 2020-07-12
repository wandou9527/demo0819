package com.wandou.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liming
 * @date 2020-07-06
 * @description
 */
public class Demo0706 {

    private List<String> strList = new ArrayList();

    @Test
    public void test() {
        String str = "abc...t..ecs.mk.....asm...";
        int length = str.length();
        String result = "";
        String temp = "";
        for (int i = 0; i < length; i++) {
            if (temp == "") {
                temp = temp + str.charAt(i);
            } else {
                if (str.charAt(i) == '.') {
                    if (temp.charAt(0) == '.') {
                        temp = temp + str.charAt(i);
                    } else {
                        result = result + temp;
                        temp = "";
                    }
                } else {
                    if (str.charAt(i) != '.') {
                        if (temp.charAt(0) != '.') {
                            temp = temp + str.charAt(i);
                        } else {
                            result = result + temp;
                            temp = "";
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    @Test
    public void test2() {
        String str = "abc...t..ecs.mk.....asm...";
        for (int i = 0; i < str.length(); i++) {
            add(str.charAt(i));
        }
        System.out.println(strList);
    }

    public void add(char ch) {
        int size = strList.size();
        if (size == 0) {
            strList.add(ch + "");
            return;
        }
        if (ch == '.') {
            if (strList.get(size - 1).charAt(0) == '.') {
                strList.add(size - 1, strList.get(size - 1) + ch);
            } else {
                strList.add(ch + "");
            }
        } else {
            //字母
            if (strList.get(size - 1).charAt(0) != '.') {
                strList.add(size - 1, strList.get(size - 1) + ch);
            } else {
                strList.add(ch + "");
            }
        }
    }

}
