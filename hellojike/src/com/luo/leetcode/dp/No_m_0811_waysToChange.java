package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

/**
 * 面试题 08.11. 硬币
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，
 * 编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 */
public class No_m_0811_waysToChange {

    /**
     * 典型动态规划问题
     * @param n
     * @return
     */
    public int waysToChange(int n) {
//        确认状态 ,n分
//        明确dp数据定义.dp[n]表示n分有dp[n]种表示法
//        状态转移 dp[n]=dp[n-1]+dp[n-5]+dp[n-10]+dp[n-25]
//          这种求的是组合问题,转移方程有误
//        base case dp[0]=0,dp[1]=1
        int[] dp=new int[n+1];
        dp[0]=1;
//        dp[1]=1;
        for (int i = 0; i <=n; i++) {
            if(i-1>=0)
                dp[i]+=dp[i-1];
            if(i-5>=0)
                dp[i]+=dp[i-5];
            if(i-10>=0)
                dp[i]+=dp[i-10];
            if(i-25>=0)
                dp[i]+=dp[i-25];
        }
        return dp[n];
    }

    /**
     * 解决组合问题
     * @param n
     * @return
     */
    public int waysToChange2(int n) {
//        明确状态 分值n
//        明确dp数组含义,dp[n]表示n分值的组合表示数
//        状态转移, 方法1的状态方程是有误的,会导致组合顺序不同而重复
//          故当前选择硬币,能选择大的就不选择小的
//          dp[n]=dp[n-coin[i]]  ,i取值可以选择的硬币列表
//        base case dp[0]=0
        int[] dp=new int[n+1];
//        这样解释
//        刚好可以用一个硬币凑成的情况，是一种情况
        dp[0]=1;
        int[] coins={1,5,10,25};
        for (int coin:coins){
            for (int i = coin; i <=n; i++) {
                dp[i]+=dp[i-coin]%1000000007;
            }
        }
        return dp[n];
    }

    /**
     * 逐步优化,由于硬币数量没有限制,这是一个完全背包问题
     * @param n
     * @return
     */
    public int waysToChange3(int n) {
//        状态,可选的硬币类型,面值
//        明确dp数组定义,dp[i][j]表示i种硬币组成分值为j的组合数
//        状态转移 dp[i][j]=dp[i-1][j]+dp[i][j-coin[i]]
//          dp[i-1][j] 表示没有选择coin[i]硬币
//          dp[i][j-coin[i]] 表示选择了coin[i]硬币
//        base case
//          dp[0][j] 没有硬币选择组成j,不可能有方案
//          dp[i][0] 组成分值为0,只有一种选择,就是所有硬币都不选择
        int[] coin={1,5,10,25};
        int[][] dp=new int[5][n+1];
        for (int i = 1; i <=4; i++) {
            dp[i][0]=1;
        }
//        进一步优化成一维 dp ，从状态转移方程可以看出，
//        dp[i][j] 仅仅和 dp[i-1]的状态有关，所以可以压缩为 1 维
//        从dp[i][j]=dp[i-1][j]+dp[i][j-coin[i]]怎么看出来的???
        for (int i = 1; i <=4; i++) {
            for (int j = 1; j <=n; j++) {
                dp[i][j]=dp[i-1][j]%1000000007;
                if(j-coin[i-1]>=0)
                    dp[i][j]=(dp[i][j]+dp[i][j-coin[i-1]])%1000000007;
            }
        }
        return dp[4][n];
    }

    /**
     * 将二维数组优化成一维数组
     * @param n
     * @return
     */
    public int waysToChange4(int n) {
        int[] coins={1,5,10,25};
//        状态,分值
//        明确dp数组含义,dp[i]表示分值i的组合数
        int[] dp=new int[n+1];
//        状态转移
//          dp[i]=dp[i]+dp[i-coin[j]]
//        base case . 当凑到分值为0,说明有一种情况
        dp[0]=1;
        for (int coin:coins){
            for (int i = 0; i <=n; i++) {
                if(i-coin>=0)
                    dp[i]=(dp[i]+dp[i-coin])%1000000007;
            }
        }

//        这么遍历就不对
//        for (int i = 0; i <=n; i++) {
//            for (int coin:coins){
//                if(i-coin>=0)
//                    dp[i]=(dp[i]+dp[i-coin])%1000000007;
//            }
//        }
        return dp[n];
    }


    public static void main(String[] args){
        No_m_0811_waysToChange test=new No_m_0811_waysToChange();
        int n=10;
        int i = test.waysToChange(n);
        System.out.println(i);

        int i1 = test.waysToChange2(n);
        System.out.println(i1);

        int i2 = test.waysToChange3(n);
        System.out.println(i2);

        int i3 = test.waysToChange4(n);
        System.out.println(i3);
    }
}
