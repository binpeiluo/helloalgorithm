package com.luo.leetcode.dp;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 */
public class No343_integerBreak {

    /**
     * 重复子问题,最优子结构
     * @param n
     * @return
     */
    public int integerBreak(int n) {
//        确定状态
//        明确dp函数,dp[i]表示数字i拆解后的最大乘积
//        状态转移, i分为j和i-j 两部分,其中假如i-j可以分解
//          dp[i]   = i * dp[i-j]  假如 i-j这部分不拆分
//                  = i * (i-j)    假如 i-j 这部分拆分
//        base case
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for (int i = 2; i <=n; i++) {
            int max=0;
            for (int j = 1; j < i; j++) {
                int temp=Math.max(j*dp[i-j],j*(i-j));
                max=Math.max(max,temp);
            }
            dp[i]=Math.max(dp[i],max);
        }
        return dp[n];
    }

    public static void main(String[] args){
        No343_integerBreak test=new No343_integerBreak();
        int i = test.integerBreak(10    );
        System.out.println(i);
    }
}
