package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class No64_minPathSum {

    /**
     * 标准动态规划
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
//        明确变量 到达的位置
//        明确dp数组含义 dp[i][j]表示从起点走到grid[i][j]的最小路径
//        状态转移 dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j]
//          所求的是 dp[m-1][n-1]
//        base case  dp[0][0]=grid[0][0]
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min=Math.min(
                        i-1>=0?dp[i-1][j]:Integer.MAX_VALUE,
                        j-1>=0?dp[i][j-1]:Integer.MAX_VALUE
                );

                dp[i][j]=grid[i][j];
                if(min!=Integer.MAX_VALUE){
                    dp[i][j]+=min;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args){
        No64_minPathSum test=new No64_minPathSum();
        int[][] grid={
                {1,3,1},
                {1,2,1},
                {4,3,2}
        };

        int i = test.minPathSum(grid);
        System.out.println(i);
    }
}
