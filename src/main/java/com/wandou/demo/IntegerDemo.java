package com.wandou.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * revert后要加点东西才能提交，revert之后提交push等去除的内容远程没有去除。。
 */

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
        int a = 2 << 1;
        System.out.println(a);

        //Integer.highestOneBit是用来获取最左边的bit（其他bit位为0）所代表的数值.
        int i = Integer.highestOneBit(10);
        System.out.println(i);

        System.out.println(Integer.max(9, 4));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(9 & 5);

        int b = 15 << 1;
        System.out.println("15 << 1: " + b);
        System.out.println("15 >> 1: " + (15 >> 1));

        System.out.println("3 << 1: " + (3 << 1));
        System.out.println("3 >> 1: " + (3 >> 1));
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
        System.out.println("stream.count() = " + stream.filter(num -> num == null).count());
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

    /**
     * 判断一个数是2的次方
     * https://www.cnblogs.com/daisy0707/p/5279078.html
     *
     * @param
     * @return
     */
    @Test
    public void isPower() {
        // 1 是 2 的 0 次方
        int n = 1;
        if (n < 1) {
            System.out.println(false);
            return;
        }
        int i = 1;
        while (i <= n) {
            if (i == n) {
                System.out.println(true);
                return;
            }
            i <<= 1;
        }
        System.out.println(false);

    }

    @Test
    public void isPower1() {
        // 1 是 2 的 0 次方
        int n = 128;
        int target = n & (n - 1);
        System.out.println(target == 0);

    }

    @Test
    public void isNumber() {
        boolean isNum = NumberUtils.isNumber("555");
        System.out.println(isNum);

        boolean isNum1 = NumberUtils.isNumber("555a");
        System.out.println(isNum1);
    }

    /**
     * 积分分表计算
     */
    @Test
    public void m7() {
        // account user_id 计算
        System.out.println(1000066836 % 32);
        System.out.println(18260 % 32);
        System.out.println(1000104836 % 32);
        // order account_id 计算
        System.out.println(new Long(381477906268524544L).hashCode() % 32);
    }
}
