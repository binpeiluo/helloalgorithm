package com.luo.leetcode.dp;


/*
    312. 戳气球
    有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
        现在要求你戳破所有的气球。每当你戳破一个气球 i 时，
        你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
        这里的 left 和 right 代表和 i 相邻的两个气球的序号。
        注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
        求所能获得硬币的最大数量。
        说明:
        你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
        0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
        示例:
        输入: [3,1,5,8]
        输出: 167
        解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/
public class No312_maxCoins {

    /**
     * 先从最简单的回溯思路实现
     * 当选择了戳破一个气球后,该气球不能再次被戳破.这可以将该位置上的气球置为-1控制.
     * 但戳破气球后向两边寻找没有被戳破的气球
     *
     * 由于是回溯算法,每一层遍历的次数为n,下层为n-1,故时间复杂度为 O(n!)
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        helper(nums,0,0);
        return res;
    }

    private int res=0;
    private void helper(int[] nums,int level,int maxCoin){
//        结束条件
        int len=nums.length;
        if(level==len){
            res=Math.max(res,maxCoin);
            return;
        }
//        遍历每一层
        for (int i = 0; i < len; i++) {
            if(nums[i]==-1)
                continue;
            int curr=nums[i];
//            代表气球被戳破
            nums[i]=-1;
//            向两边寻找气球
            int left=i-1;
            while(left>=0&&nums[left]==-1)
                left--;
            int right=i+1;
            while(right<len&&nums[right]==-1)
                right++;
            int currProfix=curr*(left==-1?1:nums[left])*(right==len?1:nums[right]);
            helper(nums,level+1,maxCoin+currProfix);
//            回溯
            nums[i]=curr;
        }
    }

    /**
     * 考虑使用动态规划优化
     * @param nums
     * @return
     */
    public int maxCoins2(int[] nums){
//        状态.   起始位置,结束位置
//        明确dp数组定义. dp[i][j]表示从位置i到位置j之间能获取的硬币最大数量
//        状态转移
//          dp[i][j]=Max(dp[i][k]+dp[k][j]+dp[i]*dp[k]*dp[j]) k=i+1~j-1
//          考虑如何遍历方向.这样子求解的是dp[0][len+1]这个值,如何遍历计算才能返回正确答案
//          因为 dp[i][j]  <-- dp[i][k] and dp[k][j] 且 i<k<j.
//          那么考虑 i从0~len+1,j从0~len+1 会导致 dp[0][len+1]是不正确的,
//          故可以考虑 i从len+1~0,j从0~len+1
//        base case ,dp[i][i+1]=0;

        int len=nums.length;
        int[] numsAfter=new int[len+2];
        System.arraycopy(nums,0,numsAfter,1,len);
        numsAfter[0]=1;
        numsAfter[len+1]=1;
        int[][] dp=new int[len+2][len+2];
        for (int i = len+1; i >=0; i--) {
            for (int j = i; j < len + 2; j++) {
                int res=0;
                for (int k = i+1; k < j; k++) {
                    res=Math.max(res,dp[i][k]+dp[k][j]+numsAfter[i]*numsAfter[k]*numsAfter[j]);
                }
                dp[i][j]=res;
            }
        }
        return dp[0][len+1];
    }


    public static void main(String[] args){
        No312_maxCoins test=new No312_maxCoins();
        int[] nums={3,1,5,8};
        int i = test.maxCoins(nums);
        System.out.println(i);

        int i1 = test.maxCoins2(nums);
        System.out.println(i1);
    }
}
