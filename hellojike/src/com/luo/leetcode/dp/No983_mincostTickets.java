package com.luo.leetcode.dp;

/**
 * 983. 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 *
 * 火车票有三种不同的销售方式：
 *
 * 一张为期一天的通行证售价为 costs[0] 美元；
 * 一张为期七天的通行证售价为 costs[1] 美元；
 * 一张为期三十天的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 *
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 * 示例 2：
 *
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * 输出：17
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
 * 你总共花了 $17，并完成了你计划的每一天旅行。
 *  
 *
 * 提示：
 *
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 *
 */
public class No983_mincostTickets {

    /**
     * 动态规划
     * 一开始想的是创建一个dp数组,长度为days的长度,但是如此处理起来是在麻烦.
     * 可以定义dp数组,长度为days最后一天旅行的天数值.
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
//        确定状态,当前天数值,旅行需要的消费
//        明确dp数组定义,dp[i]表示在一年之中的第i天旅行时,所需要的花费
//        状态转移,dp[i]=min{dp[i-1]+costs[0],dp[i-7]+costs[1],dp[i-30]+costs[2]}
//            为什么在这不是 min{dp[i-j]}+costs[1],1<=j<=7,
//            这是因为如果在dp[i-7]到dp[i-1]期间都旅行,那么dp[i-7]肯定是花费最小的一天
//        base case.    dp[0]=0

//        另外并不是每一天都需要旅行的,如何区别呢.
//        假如当天需要旅行,那么进行计算.如果不需要,那么当天的消费值等于前一天的值

        int daysLen=days.length;
        int lastDay=days[daysLen-1];
        int[] dp=new int[lastDay+1];
//        -1代表着改天需要旅行
        for (int day:days)
            dp[day]=-1;
        for (int i = 1; i <=lastDay; i++) {
            if(dp[i]==0)
//                当前不需要旅行,
                dp[i]=dp[i-1];
            else{
//                当天需要旅行
                int a=dp[i-1]+costs[0];
                int b=costs[1];
                if(i-7>=0)
                    b=dp[i-7]+costs[1];
                int c=costs[2];
                if(i-30>=0)
                    c=dp[i-30]+costs[2];
                dp[i]=Math.min(a,b);
                dp[i]=Math.min(dp[i],c);
            }
        }
        return dp[lastDay];
    }

    public static void main(String[] args){
        No983_mincostTickets test=new No983_mincostTickets();
//        int[] days = {1,4,6,7,8,20}, costs = {2,7,15};

        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31}, costs = {2,7,15};

        int i = test.mincostTickets(days, costs);
        System.out.println(i);
    }
}
