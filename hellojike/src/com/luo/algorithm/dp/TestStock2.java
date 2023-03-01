package com.luo.algorithm.dp;

/**
 * 股票买卖问题
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class TestStock2 {

    /**
     * 这道题跟限制一次的交易不同,可以多次交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
//        状态    天数,是否手上持股.这里无限制交易次数,所以交易次数的状态可以删除
//        选择    买入,卖出,无操作
//        状态转移
//        base case
        int len=prices.length;
        if(len<2)
            return 0;
        int[][] dp=new int[len][2];
//        base case
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[len-1][0];
    }

    /**
     * 优化
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices){
        int len=prices.length;
        if(len<2)
            return 0;
        int p1,p2,p3;
        p1=0;
        p2=-prices[0];
        p3=0;
        for (int i = 1; i < len; i++) {
            p3=p1;
            p1=Math.max(p1,p2+prices[i]);
            p2=Math.max(p2,p3-prices[i]);
        }
        return p1;
    }

    public static void main(String[] args){
        TestStock2 test=new TestStock2();
        int[] stocks={7,1,5,3,6,4};
        int i = test.maxProfit(stocks);
        System.out.println(i);

        int i1 = test.maxProfit2(stocks);
        System.out.println(i1);
    }
}
