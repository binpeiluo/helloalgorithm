package com.luo.leetcode.array;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class No53_maxSubArray {

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums.length==0)
            return 0;
        int res=nums[0],pre=0;
        for (int x:nums){
            pre=Math.max(pre+x,x);
            res=Math.max(res,pre);
        }
        return res;
    }

    /**
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums){
        return helper2(nums,0,nums.length-1)[3];
    }

    /**
     *
     * @param nums
     * @param left
     * @param right
     * @return  返回nums数组中从left到right元素的统计信息。0 总和，1 以左边界为起点的子段和，2以右边界为起点的子段和，3最大子段和
     */
    private int[] helper2(int[] nums,int left,int right){
        if(left==right)
            return new int[]{nums[left],nums[left],nums[left],nums[left]};
        int mid=left+(right-left)/2;
        int[] leftRes = helper2(nums, left, mid);
        int[] rightRes = helper2(nums, mid + 1, right);
        return merge2(leftRes,rightRes);
    }

    private int[] merge2(int[] leftRes,int[] rightRes){
        int iSum=leftRes[0]+rightRes[0];
        int lSum=Math.max(leftRes[1],leftRes[0]+rightRes[1]);
        int rSum=Math.max(rightRes[2],rightRes[0]+leftRes[2]);
        int mSum=Math.max(Math.max(leftRes[3],rightRes[3]),leftRes[2]+rightRes[1]);
        return new int[]{iSum,lSum,rSum,mSum};
    }


    public static void main(String[] args){
        No53_maxSubArray test=new No53_maxSubArray();
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        int i = test.maxSubArray(nums);
        System.out.println(i);

        int i1 = test.maxSubArray2(nums);
        System.out.println(i1);
    }
}
