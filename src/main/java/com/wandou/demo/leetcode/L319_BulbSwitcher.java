package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/9/26
 * @description 319. 灯泡开关
 */
public class L319_BulbSwitcher {

    /**
     * 求平方根
     */
    @Test
    public void test() {
        int n = 17;
        System.out.println((int) Math.sqrt(n));
        System.out.println("bulbSwitch(n) = " + bulbSwitch(n));
    }

    /**
     * 结果应该是对的，在LeetCode提交，运行时间超限
     *
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n];
        for (int i = 0; i < n; i++) {
            bulbs[i] = true;
        }
        // i 表示轮数
        for (int i = 2; i <= n; i++) {
            // 每轮内哪些数组位置要变，每i个灯泡变化一次。j从第2个开始，2-1刚好是索引
            for (int j = 2; j <= n; j++) {
                if (j % i == 0) {
                    bulbs[j - 1] = !bulbs[j - 1];
                }
            }
        }
        int brightCount = 0;
        for (int i = 0; i < n; i++) {
            if (bulbs[i]) {
                brightCount++;
            }
        }
        return brightCount;
    }

}
