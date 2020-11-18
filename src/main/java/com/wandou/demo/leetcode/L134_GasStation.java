package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/11/18
 * @description 134. 加油站
 * <p>
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * <p>
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L134_GasStation {

    @Test
    public void test() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int target = canCompleteCircuit(gas, cost);
        System.out.println("target = " + target);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int l = gas.length;
        for (int i = 0; i < l; i++) {
            int cur = gas[i] - cost[i];
            if (cur < 0) {
                continue;  //起始站都过不了，直接跳过
            }
            boolean success = true;
            for (int j = i + 1; j % l != i; j++) {   //开始启程，知道走到起点
                int next = gas[j % l] - cost[j % l];
                if (cur + next < 0) {  //下一站走不过
                    if (j >= l) {
                        return -1;  //如果已经走到数组的开头，说明不再可能成功
                    }
                    success = false;
                    i = j;   //还没走到开头，后面一段路还有机会，从next的下一站从头开始
                    break;
                }
                cur += next;
            }
            if (success) {
                return i;
            }
        }
        return -1;
    }
}
