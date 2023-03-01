package com.luo.labuladong.dp.robber;

/**
 * 打家劫舍系列
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 */
public class HouseRobber1 {

    public int rob(int[] nums) {
//        动态规划核心,穷举
//        三要素,重叠子问题,最优子结构,状态转移

//        明确状态,打劫位置
//        明确dp函数含义,dp[i]=k表示打劫房屋i和之前房屋能获取的最大收益
//        状态转移  dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i])
//                  dp[i-1]表示当前不打劫当前房屋
//                  dp[i-2]+nums[i] 表示打劫了当前房屋
//        base case dp[0]=nums[0],dp[i]=0 if i<0

//        其实可以做优化,因为dp[i]只与dp[i-1],dp[i-2]有关
        int len=nums.length;
        if(len==0){
            return 0;
        }
        int[] dp=new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else if (i == 1) {
                dp[i] = Math.max(dp[i - 1], nums[i]);
            } else {
                if (i - 2 >= 0) {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
                }
            }
        }
        return dp[len - 1];
    }


    public int rob2(int[] nums) {
//        从左到右走过房子,在每个房子前有两种选择.选择打劫该房屋,那么就需要跳过一个房屋
//        如果没有打劫该房屋,那么可以打劫下一个房屋
        return helper2(nums,0);
    }

    private int helper2(int[] nums,int start){
        if(start>=nums.length){
            return 0;
        }
        int max=Math.max(helper2(nums,start+1),
                nums[start]+helper2(nums,start+2));
        return max;
    }

    public static void main(String[] args){
        HouseRobber1 test=new HouseRobber1();
        int[] nums={2,7,9,3,1};
        int rob = test.rob(nums);
        System.out.println(rob);

        int i = test.rob2(nums);
        System.out.println(i);
    }
}
