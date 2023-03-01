package com.luo.leetcode.dp;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 */
public class No309_maxProfit {

    /**
     * 股票买卖系列,不同的是有冻结期.买完后第二天无法交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
//        确定状态,天数,持股状态
//        确定选择, 1买入(不能持有股票才能买入) 2卖出(必须持有股票才能卖出,卖出后第二天无法买入) 3无操作
//        状态转移
//          dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
//          dp[i][1]=max(dp[i-1][1],dp[i-2][0]-prices[i])
//        为什么这里使用i-2.

        int len=prices.length;
        if(len==0){
            return 0;
        }
        int[][] dp=new int[len][2];
        for (int i = 0; i < len; i++) {
            dp[i][0]=Math.max(
                    i-1<0?0:dp[i-1][0],
                    (i-1<0?Integer.MIN_VALUE:dp[i-1][1])+prices[i]
            );
            dp[i][1]=Math.max(
                    i-1<0?Integer.MIN_VALUE:dp[i-1][1],
                    (i-2<0?0:dp[i-2][0])-prices[i]
            );
        }
        return dp[len-1][0];
    }

    public static void main(String[] args){
        No309_maxProfit test=new No309_maxProfit();
        int[] prices={1,2,3,0,2};

        int i = test.maxProfit(prices);
        System.out.println(i);

    }
}
