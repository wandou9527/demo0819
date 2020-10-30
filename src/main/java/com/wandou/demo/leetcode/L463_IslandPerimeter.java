package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/10/30
 * @description 463. 岛屿的周长 【简单】
 * https://leetcode-cn.com/problems/island-perimeter/
 * 独立思考出来的
 */
public class L463_IslandPerimeter {

    @Test
    public void test() {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}};
        int islandPerimeter = islandPerimeter(grid);
        System.out.println("islandPerimeter = " + islandPerimeter);
    }

    public int islandPerimeter(int[][] grid) {
        //答案 周长
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 陆地
                perimeter += 4;
                int upIdx = i - 1;
                int downIdx = i + 1;
                if (upIdx >= 0 && upIdx < grid.length && grid[upIdx][j] == 1) {
                    perimeter -= 1;

                }
                if (downIdx >= 0 && downIdx < grid.length && grid[downIdx][j] == 1) {
                    perimeter -= 1;
                }
                int leftIdx = j - 1;
                int rightIdx = j + 1;
                if (leftIdx >= 0 && leftIdx < grid[i].length && grid[i][leftIdx] == 1) {
                    perimeter -= 1;
                }
                if (rightIdx >= 0 && rightIdx < grid[i].length && grid[i][rightIdx] == 1) {
                    perimeter -= 1;
                }
            }
        }
        return perimeter;
    }

}
