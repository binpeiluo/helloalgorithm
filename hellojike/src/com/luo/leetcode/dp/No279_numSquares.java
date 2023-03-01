package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 */
public class No279_numSquares {

    /**
     * 使用动态规划
     * @param n
     * @return
     */
    public int numSquares(int n) {
//        状态,数值n
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
//        明确dp数组定义 dp(n) 表示数值n的完全平方数
//        转移方程 dp(n)=min{dp(n-k)+1}, k=1,4,9,...
//        base case
        dp[0]=0;
        dp[1]=1;
        int sqrt= (int) Math.sqrt(n);
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=sqrt; j++) {
                if(i-j*j>=0)
                    dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args){
        No279_numSquares test=new No279_numSquares();
        int n=13;
        int i = test.numSquares(n);
        System.out.println(i);
    }
}
