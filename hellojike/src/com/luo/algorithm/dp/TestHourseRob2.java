package com.luo.algorithm.dp;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 */
public class TestHourseRob2 {
    /**
     * 与第一道不同之处在于这是圆形的房子.首尾相连
     * 首尾不可以都选择,那么可以计算 0~len-2 和 1~len-1 两部分的最大值
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
//        定义dp[i]为从nums[i]位置开始能抢的最大金额
        int len=nums.length;
        if(len<=2)
            return 0;
        int[] dp1=new int[len+2];
//        dp1[i]=max(dp1[i+1],nums[i]+dp1[i+2])
//        计算从0~len-2
        for (int i = len-2; i >=0; i--) {
            dp1[i]=Math.max(dp1[i+1],nums[i]+dp1[i+2]);
        }
//        计算从1~len-1
        int[] dp2=new int[len+2];
        for (int i = len-1; i >=1; i--) {
            dp2[i]=Math.max(dp2[i+1],nums[i]+dp2[i+2]);
        }
        return Math.max(dp1[0],dp2[1]);
    }

    /**
     * 优化空间
     * @param nums
     * @return
     */
    public int rob2(int[] nums){
        int len=nums.length;
        if(len==0)
            return 0;
        if(len==1)
            return nums[0];
        int t1,t2;
        t1=t2=0;
        int r=0;
        for(int i=len-2;i>=0;i--){
            r=Math.max(t1,nums[i]+t2);
            t2=t1;
            t1=r;
        }

        int m1,m2;
        m1=m2=0;
        int p=0;
        for (int i = len-1; i >=1; i--) {
            p=Math.max(m1,nums[i]+m2);
            m2=m1;
            m1=p;
        }
        return Math.max(r,p);
    }


    public static void main(String[] args){
        TestHourseRob2 test=new TestHourseRob2();
        int[] nums={1,2};
        int rob = test.rob(nums);
        System.out.println(rob);

        int i = test.rob2(nums);
        System.out.println(i);
    }

}
