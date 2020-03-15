package com.luo.algorithm.dp;

/**
 * 股票买卖系列
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class TestStock1 {

    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
//        状态,第几天
//        选择,买入,卖出,无操作.选择有三种,但是无操作也分持有股无操作,没持有时无操作.所以可以在状态添加一个是否持有股票
//        状态转移
//        base case.
        int len=prices.length;
        if(len<2)
            return 0;
        int[][] dp=new int[len][2];
        for (int i = 0; i < len; i++) {
            if(i-1==-1){
                dp[0][0]=0;
                dp[0][1]=-prices[0];
                continue;
            }
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
//            这里的交易次数限制为1
            dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[len-1][0];
    }

    /**
     * 优化dp数组,这里依赖 dp[i][0]依赖dp[i-1][0]和dp[i-1][1]
     *                  dp[i][1]依赖dp[i-1][1]
     * 将空间复杂度由O(n)将为O(1)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices){
        int len=prices.length;
        if(len<2)
            return 0;
        int p1,p2;
        p1=p2=0;
        p1=0;
        p2=-prices[0];
        for (int i = 1; i < len; i++) {
            p1=Math.max(p1,p2+prices[i]);
            p2=Math.max(p2,-prices[i]);
        }
        return p1;
    }

    public static void main(String[] args){
        TestStock1 test=new TestStock1();
        int[] stocks={7,1,5,3,6,4};
        int i = test.maxProfit(stocks);
        System.out.println(i);

        int i1 = test.maxProfit2(stocks);
        System.out.println(i1);
    }
}
