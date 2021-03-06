package com.luo.labuladong.dp.stock;

/**
 * 买卖股票问题
 * 188. 买卖股票的最佳时机 IV
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 *
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class StockProfix4 {


    public int maxProfit(int k, int[] prices) {
//        此题是股票买卖系列中最具有共性的一题
//        动态规划的实质就是枚举所有状态,根据对应的选择更新状态
//        对于每一天的选择,有三种选择.买入,卖出,无操作.但是每一种操作是有条件的,比如只有在持有股票的情况才能卖出.选择依赖状态
//        那该题中的所有状态有三个,天数,操作次数,以及持有股票状态
//        dp[n][k][2]
//        for 0<=i<n:
//          for 0<=j<k:
//              for s in [0,1]:
//                  dp[i][j][s]=max(买入,卖出,无操作)
//        很明显,题目是求 dp[n-1][k][0]

//        状态转移方程
//        dp[n][k][0]=max(  dp[n-1][k][0],  dp[n-1][k][1]+prices[n])
//                          无操作             卖出
//        今日没有持有股票,那么可能是昨天就没有持有,那么是昨天持有,然后今天卖出
//        dp[n][k][1]=max(  dp[n-1][k][1],  dp[n-1][k-1][0]-prices[n])
//                          无操作           买入
//        今日持有股票,那么可能是昨天就持有股票,那么是昨天没有持有,然后今日买入

//        base case. 区别那种情况是0,那种情况是负无穷(表示不存在)
//        dp[-1][k][0]=0 表示还没开始时收益为0
//        dp[-1][k][1]=负无穷,表示这种情况不会发送
//        dp[n][0][0]=0 表示还没开始 收益为0
//        dp[n][0][1]=负无穷,表示不会有这种情况

        int len=prices.length;
        int[][][] dp=new int[len][k][2];
//        枚举所有状态
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < 2; l++) {

//                    第i天没持有时的收益==max(第i-1天没持有的收益,第i-1天持有收益+prices[i])
//                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
//                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);

                    dp[i][j][0]=Math.max(
                            i-1<0?0:dp[i-1][j][0],
                            (i-1<0?Integer.MIN_VALUE:dp[i-1][j][1])+prices[i]
                    );
                    dp[i][j][1]=Math.max(
                            i-1<0?Integer.MIN_VALUE:dp[i-1][j][1],
                            ((i-1<0||j-1<0)?0:dp[i-1][j-1][0])-prices[i]
                    );
                }
            }
        }
        return dp[len-1][k-1][0];

    }

    public static void main(String[] args){
        StockProfix4 test=new StockProfix4();
//        int[] prices={2,4,1};
//        int k=2;
        int[] prices={3,2,6,5,0,3};
        int k=2;
        int i = test.maxProfit(k, prices);
        System.out.println(i);
    }


}
