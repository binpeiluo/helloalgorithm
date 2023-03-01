package com.luo.leetcode.array;

/**
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */
public class No413_numberOfArithmeticSlices {

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
//                这里可以理解为 多了这个元素组成等差数组。多了这个元素可以多组成多少个等差数组
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }


    public int numberOfArithmeticSlices2(int[] nums) {
        int len=nums.length;
        if(len<3){
            return 0;
        }
        int res=0;
        int d=nums[1]-nums[0];
        int maxLen=2;
        for(int i=2;i<len;i++){
            if(nums[i]-nums[i-1]==d){
                maxLen++;
            }else{
                res+=(maxLen-2)*(maxLen-1)/2;
                d=nums[i]-nums[i-1];
                maxLen=2;
            }
        }
        res+=(maxLen-2)*(maxLen-1)/2;
        return res;
    }

    /**
     * 动态规划
     * 核心思想
     *      dp[i] 表示已nums[i]为结尾的子数组组成等差数组长度, 如果 i+1 能组成子数组
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices3(int[] nums) {
        int len=nums.length;
        if(len<2){
            return 0;
        }
        int[] dp=new int[len];
        int result=0;
        for (int i = 2; i < len; i++) {
            if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){
                dp[i]=dp[i-1]+1;
                result+=dp[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        No413_numberOfArithmeticSlices test=new No413_numberOfArithmeticSlices();
        int[] nums={1,2,3,4,5,6};
        int i = test.numberOfArithmeticSlices(nums);
        int i2 = test.numberOfArithmeticSlices2(nums);
        int i3 = test.numberOfArithmeticSlices3(nums);
        System.out.printf("i=%d, i2=%d, i3=%d", i, i2, i3);
    }
}
