package com.luo.algorithm.dp;

public class TestHourseRob {
    public int rob(int[] nums) {
        return dp(nums,nums.length-1);
    }

    private int dp(int[] nums,int i){
        if(i<0)
            return 0;
        return Math.max(dp(nums,i-2)+nums[i],dp(nums,i-1));
    }

    public int rob2(int[] nums){
        int len=nums.length;
        if(len==0)
            return 0;
        if(len==1)
            return nums[0];
        int[] dp=new int[len];
//        bad case

        dp[0]=nums[0];
        for (int i = 1; i < len; i++) {
            if(i-2>=0)
                dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
            else
                dp[i]=Math.max(nums[i],dp[i-1]);
        }
        return Math.max(dp[len-1],dp[len-2]);
    }

    public int rob3(int[] nums){
//        定义dp[i]为从nums[i]开始能抢劫的最大金额
        int len=nums.length;
        int[] dp=new int[len+2];
        for (int i = len-1; i >=0; i--) {
            dp[i]=Math.max(nums[i]+dp[i+2],dp[i+1]);
        }
        return dp[0];
    }

    public static void main(String[] args){
        TestHourseRob test=new TestHourseRob();
//        int[] nums={1,2,3,1};
        int[] nums={2,7,9,3,1};
        int rob = test.rob(nums);
        System.out.println(rob);

        int i = test.rob2(nums);
        System.out.println(i);
    }
}
