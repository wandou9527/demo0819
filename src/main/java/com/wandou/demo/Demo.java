package com.wandou.demo;

import com.wandou.enums.AEnum;
import com.wandou.enums.AlipayTradeStatusEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.servlet.GenericServlet;
import java.util.*;

/**
 * @author liming
 * @date 2018/9/21 15:54
 * @description
 * @modify
 */

public class Demo {
    public static void main(String[] args) {
        boolean b = ifStr("22");
        System.out.println(b);
        m2();
    }

    public static boolean ifStr(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static void m2() {
        Map map = new LinkedHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(new Random().nextInt(), RandomStringUtils.randomNumeric(8));
        }
        System.out.println(map);
    }

    /**
     * jia shang 2018年8月19日
     */

    /**
     * https://blog.csdn.net/tiwerbao/article/details/42836305 apache common
     */
    @Test
    public void m3() {
        int[] arr1 = {1, 5, 8};
        int[] arr2 = {5, 8, 1};
        boolean equals = Arrays.equals(arr1, arr2);
        System.out.println(equals);//false

        boolean equals1 = ArrayUtils.isEquals(arr1, arr2);
        System.out.println(equals1);//false


    }

    @Test
    public void m4List() {
        final List<Integer> list = new ArrayList();
        list.add(6);
        list.add(9);
        list.add(77);

        for (Integer i : list) {
            System.out.println(i);
        }

//        list.stream().forEach( i () -> { System.out.println(i)});

        Class<GenericServlet> genericServletClass = GenericServlet.class;
    }

    @Test
    public void m5E() {
        String name = AEnum.A.name();//名字内部原生的的名字
        int ordinal = AEnum.A.ordinal();//内部自动排序的序号
        System.out.println(AEnum.A);
        System.out.println(name);
        System.out.println(ordinal);
        System.out.println(AEnum.A.getCode());
        System.out.println(AEnum.A.getDescription());
        System.out.println(AEnum.getByCode(0));
        System.out.println(AEnum.getByCode(2));
        System.out.println(AEnum.getByCode(3));
        System.out.println("#################");
    }

    @Test
    public void m6BEnum() {
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED);
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED.getDescription());
        System.out.println(AlipayTradeStatusEnum.TRADE_CLOSED.getDeclaringClass());

    }

    //测验的基础，公共的（下面有：a 分支 看能否保留；）
    //a 分支 看能否保留
    //a 分支 b分支在此行写代码 看能否冲掉
    //验证结果：同一账号，在a分支先写，在b分支后写，将b合入a，会冲突，是有现象的，不会默默的合并

}
