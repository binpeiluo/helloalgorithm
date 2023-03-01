package com.luo.algorithm.dp;

/**
 * 股票买卖系列
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */
public class TestStock3 {

    /**
     * 股票系列,最多能完成两笔交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len=prices.length;
        if(len<2)
            return 0;
//        状态. 天数,交易次数,是否手上持股,比起前面两道题.这里的状态多了个交易次数.
//        选择. 一样
//        bad case
//            dp[-1][k][0]=0    代表还没开始时,收益为0
//            dp[-1][k][0]=-infinity    代表还没开始时,不可能有收益
//            dp[i][0][0]=0     代表第一笔交易前,没有收益
//            dp[i][0][1]=-infinity      代表第一笔交易前,不可能有收益
        int dealCnt=2;
        int[][][] dp=new int[len][dealCnt+1][2];

        for (int i = 0; i < len; i++) {
            for (int j = dealCnt; j >=1; j--) {
                if(i==0){//第一天,无论多少笔交易都是可能达成的
//                    开始之前或者第一笔交易之前,不持股,收益为0
                    dp[0][j][0]=0;
                    dp[i][0][0]=0;
//                    开始之前或者第一笔交易之前,持股,收益为-M
                    dp[0][j][1]=Integer.MIN_VALUE;
                    dp[i][0][1]=Integer.MIN_VALUE;
                    continue;
                }
                dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
            }
        }
        return dp[len-1][dealCnt][0];
    }

    public static void main(String[] args){
        TestStock3 test=new TestStock3();
        int[] stocks={3,3,5,0,0,3,1,4};
        int i = test.maxProfit(stocks);
        System.out.println(i);
    }
}
