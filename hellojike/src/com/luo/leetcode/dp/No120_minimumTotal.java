package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */
@SuppressWarnings("Duplicates")
public class No120_minimumTotal {

    /**
     * 动态规划 重复子问题 最优子结构
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
//        状态,当前位置
//        选择,每一个位置可以选择两种方式
//        明确dp定义,dp[i][j] 表示从起点移动到triangle[i][j]时经过的路径和
//        状态转移,dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])
//
        int m=triangle.size();
        if(m==0){
            return 0;
        }
        int n=triangle.get(m-1).size();
        int[][] dp=new int[m][n];
        int res=Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if(i==0&&j==0){
                    dp[i][j]=triangle.get(i).get(j);
                }else if(j==0){
                    dp[i][j]=dp[i-1][j]+triangle.get(i).get(j);
                }else if(j==i){
                    dp[i][j]=dp[i-1][j-1]+triangle.get(i).get(j);
                }else{
                    dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
                }
                if(i==m-1){
                    res=Math.min(res,dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * 实际上辅助dp数组不需要二维.dp[i][j] <-- dp[i-1][j-1] 和 dp[i-1][j]
     * 所以只需要一维的辅助数组即可,依赖关系变为 dp[i] <-- dp[i-1] 和 dp[i]
     * 需要注意,计算的方向.假如dp计算是从左到右,那么就会计算错误.因为dp[i] 依赖前一个值.
     * 所以计算dp需要反向计算
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int m=triangle.size();
        if(m==0){
            return 0;
        }
        int n=triangle.get(m-1).size();
        int[] dp=new int[n];
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i; j >=0; j--) {
                if(i==0&&j==0){
                    dp[j]=triangle.get(i).get(j);
                }else if(j==0){
                    dp[j]=dp[j]+triangle.get(i).get(j);
                }else if(j==i){
                    dp[j]=dp[j-1]+triangle.get(i).get(j);
                }else{
                    dp[j]=Math.min(dp[j-1],dp[j])+triangle.get(i).get(j);
                }
                if(i==m-1){
                    res=Math.min(res,dp[j]);
                }
            }
        }
        return res;
    }


    public static void main(String[] args){
        No120_minimumTotal test=new No120_minimumTotal();
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );
        int i = test.minimumTotal(lists);
        System.out.println(i);

        int i1 = test.minimumTotal2(lists);
        System.out.println(i1);
    }
}
