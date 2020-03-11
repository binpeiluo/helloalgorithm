package com.luo.algorithm.binsearch;

/**
 * 二分应用问题
 *
 *
 */
public class LengthOfLIS {
    /**
     * 寻找最长子序列长度问题,使用动态规划
     *
     * 时间复杂度:   O(n^2)
     * 空间复杂度:   O(n)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums){
        int len=nums.length;
        if(len<=1)
            return len;
        int res=1;
        int[] dp=new int[len];
        for (int i = 0; i < len; i++) {
            int r=1;
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    r=Math.max(r,dp[j]+1);
                }
            }
            dp[i]=r;
            res=Math.max(res,r);
        }
        return res;
    }

    /**
     * 使用二分查找实现,结合耐心排序
     * 耐心排序的基本思路是遍历数组,将数据分成若干堆.
     * 在遍历时,点数小的只能压在点数大的s上边.假如当前点数较大,则可新建一堆.
     * 如果有多个位置可以选择,那么放置在左边.
     * 为什么放置在左边,这是为了保证堆顶元素有序
     *
     * 时间复杂度:   O(nlogn)
     * 空间复杂度:   O(n)
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums){
        int len=nums.length;
        if(len<=1)
            return len;
//        堆顶
        int[] top=new int[len];
//        堆数
        int piles=0;
        for (int i = 0; i < len; i++) {
//            当前元素
            int poker=nums[i];
            int left=0,right=piles;
            while(left<right){
                int mid=left+(right-left)/2;
                if(poker<=top[mid])
                    right=mid;
                else
                    left=mid+1;
            }
            top[left]=poker;
            if(left==piles)
                piles++;
        }
        return piles;
    }


    public static void main(String[] args){
        LengthOfLIS test=new LengthOfLIS();
//        int[] nums={6,3,5,10,11,2,9,14,13,7,4,8,12};
        int[] nums={1,2,3,4};
        int i = test.lengthOfLIS(nums);
        System.out.println(i);

        int i1 = test.lengthOfLIS2(nums);
        System.out.println(i1);
    }
}
