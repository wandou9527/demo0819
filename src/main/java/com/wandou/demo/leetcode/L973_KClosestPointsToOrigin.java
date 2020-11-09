package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author liming
 * @date 2020/11/9 每日打卡
 * @description 973. 最接近原点的 K 个点【中等】
 * <p>
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * <p>
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * <p>
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 */
public class L973_KClosestPointsToOrigin {

    @Test
    public void test() {
        int[][] points = {{1, 3}, {-2, 2}};
        int K = 1;

        int[][] points1 = {{3, 3}, {5, -1}, {-2, 4}};
        int K1 = 2;

        int[][] points2 = {{2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {1, 1}};
        int K2 = 1;

        int[][] points3 = {{1, 2}, {2, 5, 8}, {4, 5, 6, 7}, {5, 9}};
        System.out.println("points3 = " + Arrays.deepToString(points3));

        int[][] arr = kClosest(points, K);
        System.out.println("arr = " + Arrays.deepToString(arr));

        int[][] arr1 = kClosest(points1, K1);
        System.out.println("arr1 = " + Arrays.deepToString(arr1));

        int[][] arr2 = kClosest(points2, K2);
        System.out.println("arr2 = " + Arrays.deepToString(arr2));
    }

    /**
     * 排序和优先级队列都可以实现
     * 横纵轴绝对值平方相加再开根号为到原点距离
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (Math.abs(o2[0] * o2[0]) + Math.abs(o2[1] * o2[1])) - (Math.abs(o1[0] * o1[0]) + Math.abs(o1[1] * o1[1]));
            }
        });
        for (int i = 0; i < points.length; i++) {
            priorityQueue.add(points[i]);
            if (priorityQueue.size() > K) {
                priorityQueue.poll();
            }
        }
        int[][] arr = new int[K][2];
        priorityQueue.toArray(arr);
        return arr;
    }

    /**
     * 官方
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV2(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        for (int i = 0; i < K; ++i) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = K; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; ++i) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }
}
