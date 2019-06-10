package com.wandou.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerDemo {

    private AtomicInteger atomicInteger = new AtomicInteger(10);

    /**
     * Integer -128~127 会缓存
     * https://www.cnblogs.com/xiehongwei/p/7595520.html
     */
    @Test
    public void m1() {
        Integer i1 = 6;
        Integer i2 = 6;
        System.out.println("Integer 6 == 6 ? " + (i1 == i2));//true

        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println("Integer 129 == 129 ? " + (i3 == i4));//false
        System.out.println("Integer 129 equals 129 ? " + i3.equals(i4));//true
    }

    /**
     * 位运算
     */
    @Test
    public void weiYunSuan() {
        int a = 2 << 4;
        System.out.println(a);

        //Integer.highestOneBit是用来获取最左边的bit（其他bit位为0）所代表的数值.
        int i = Integer.highestOneBit(10);
        System.out.println(i);

        System.out.println(Integer.max(9, 4));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(9 & 5);
    }

    @Test
    public void m3() {
        int min = NumberUtils.min(6, 4, 9);
        System.out.println(min);
        NumberUtils.min(new int[]{2, 4, 9});

        System.out.println(Integer.min(7, 3));
    }

    /**
     * 原子 integer
     */
    @Test
    public void m4() {
        System.out.println(atomicInteger);
        atomicInteger.incrementAndGet();
        boolean b = atomicInteger.compareAndSet(1, 2);
    }

    /**
     * Stream
     * https://www.cnblogs.com/aoeiuv/p/5911692.html
     */
    @Test
    public void m5() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());

        Stream<Integer> stream = nums.stream();
//        stream.collect(Collectors.toMap(a -> a + 5, a -> a));
        Stream<Integer> filterStream = stream.filter(a -> a != null);
        //第一个函数生成一个新的ArrayList实例；
        //第二个函数接受两个参数，第一个是前面生成的ArrayList对象，二个是stream中包含的元素，函数体就是把stream中的元素加入ArrayList对象中。第二个函数被反复调用直到原stream的元素被消费完毕；
        //第三个函数也是接受两个参数，这两个都是ArrayList类型的，函数体就是把第二个ArrayList全部加入到第一个中；
        System.out.println(filterStream.collect(() -> new ArrayList<Integer>(),
                (list, item) -> list.add(item),
                (list1, list2) -> list1.addAll(list2)));
        //与上边一样的效果
        System.out.println(filterStream.collect(Collectors.toList()));

        Stream<Integer> distinctStream = nums.stream().distinct();

        //注意：sum方法不是所有的Stream对象都有的，只有IntStream、LongStream和DoubleStream是实例才有。
        IntStream intStream = nums.stream().distinct().mapToInt(a -> a);
        int sum = intStream.sum();

    }

    @Test
    public void m6() {
        int a = 127;
        int b = a / 60;
        System.out.println(b);
    }
}
