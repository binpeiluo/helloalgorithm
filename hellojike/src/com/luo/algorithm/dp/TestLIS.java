package com.luo.algorithm.dp;

/**
 *  使用动态规划解决最长递增子序列问题
 */
public class TestLIS {
    /**
     * 最长递增子序列
     *      动态规划的一般形式是求最值,核心是穷举.
     *      性质
     *          重复子问题
     *          最优子结构 --> 依赖于子问题相互独立
     *
     * @param nums
     * @return
     */
    public int calcLIS(int[] nums){
//        1 明确状态. 明确问题和子问题之间变化的变量. 在这里是以哪个字符结尾
//        2 明确dp数组定义.以角标i字符为结尾的子序列,最长的递增子序列长度为 dp[i]
//        3 明确选择,并且择优
//        4 明确base case 每个字符结尾的子序列dp[i]至少为1

        int[] dp=new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i]=1;
        }
        int max=0;
        for (int i = 0; i < dp.length; i++) {
            int res=1;
            for (int j = i-1; j >=0 ; j--) {
                if(nums[i]>nums[j]){
                    res=Math.max(res,dp[j]+1);
                }
            }
            dp[i]=res;
            max=Math.max(max,res);
        }
        return max;
    }

    public static void main(String[] args){
        TestLIS test=new TestLIS();
        int[] nums={1,4,3,4,2,3};
        int i = test.calcLIS(nums);
        System.out.println(i);
    }

}
