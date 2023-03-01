package com.luo.leetcode.slidingwindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *  
 *
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class No209_minSubArrayLen {

    /**
     * navie想法是暴力,复杂度n^2
     * 可以使用滑动窗口
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int len=nums.length;
        if(len==0){
            return 0;
        }
        int window=0,left=0,right=0;
        int result=Integer.MAX_VALUE;
        while(right<len){
            window+=nums[right];
            while(window>=s){
                result=Math.min(result,right-left+1);
                window-=nums[left++];
            }
            right++;
        }
        return result==Integer.MAX_VALUE?0:result;
    }

    /**
     * 要实现 O(nlogn) 复杂度算法,那就是二分.
     * 使用前缀和,然后二分查找.但是该值必须是正整数
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int len=nums.length;
        if(len==0){
            return 0;
        }
//        prefix[i] 表示 nums[0]~nums[i-1] 的和
        int[] prefix=new int[len+1];
        int result=Integer.MAX_VALUE;
        for (int i = 1; i <=len; i++) {
            prefix[i]=prefix[i-1]+nums[i-1];
        }
        for (int i = 0; i < len; i++) {
//            假设从位置i起始的数组,我们要找的位置是 prefix[bound]-prefix[i]>=s
            int target=s+prefix[i];
            int left=0,right=len;
//            二分查找
            while(left<right){
                int mid=left+(right-left)/2;
                if(prefix[mid]==target){
                    right=mid;
                }else if(prefix[mid]<target){
                    left=mid+1;
                }else if(prefix[mid]>target){
                    right=mid;
                }
            }
            if(prefix[right]>=target){
                result=Math.min(result,right-i);
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }

    public static void main(String[] args){
        No209_minSubArrayLen test=new No209_minSubArrayLen();
        int s=10;
        int[] nums={2,3,1,2,4,3};

        int i = test.minSubArrayLen(s, nums);
        System.out.println(i);

        int i1 = test.minSubArrayLen2(s, nums);
        System.out.println(i1);
    }
}
