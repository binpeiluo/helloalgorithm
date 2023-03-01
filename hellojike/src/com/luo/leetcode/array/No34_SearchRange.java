package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/*
    34. 在排序数组中查找元素的第一个和最后一个位置
    给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
        你的算法时间复杂度必须是 O(log n) 级别。
        如果数组中不存在目标值，返回 [-1, -1]。
        示例 1:
        输入: nums = [5,7,7,8,8,10], target = 8
        输出: [3,4]
        示例 2:
        输入: nums = [5,7,7,8,8,10], target = 6
        输出: [-1,-1]
*/
public class No34_SearchRange {

    /**
     * 思路:
     *      使用两个指针,通过二分法.注意两个指针的移动,如何控制移动到第一个元素和移动到最后一个元素
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<1)
            return new int[]{-1,-1};
        int resultLeft=this.leftBound(nums,target);
        int resultRight=this.rightBound(nums,target);
        return new int[]{resultLeft,resultRight};
    }

    private int leftBound(int[] nums,int target){
        if(nums.length<1)
            return -1;
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid-1;
            else{
                if(mid==0||nums[mid-1]!=target)
                    return mid;
                else
                    right=mid-1;
            }
        }
        return -1;
    }

    private int rightBound(int[] nums,int target){
        if(nums.length<1)
            return -1;
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid-1;
            else {
                if(mid==nums.length-1||nums[mid+1]!=target)
                    return mid;
                else
                    left=mid+1;
            }
        }
        return -1;
    }

    public int[] searchRange2(int[] nums,int target){
        if(nums.length<1)
            return new int[]{-1,-1};
        int resultLeft=this.leftBound2(nums,target);
        int resultRight=this.rightBound2(nums,target);
        return new int[]{resultLeft,resultRight};
    }

    public int leftBound2(int[] nums,int target){
        if(nums.length<1)
            return -1;
        int left=0;
        int right=nums.length;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                right=mid;
            else if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid;
        }
        if(left==nums.length)
            return -1;
        return nums[left]==target?left:-1;
    }

    public int rightBound2(int[] nums,int target){
        if(nums.length<1)
            return -1;
        int left=0;
        int right=nums.length;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                left=mid+1;
            else if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid;
        }
        if(left==0)
            return -1;
        return nums[left-1]==target?left-1:-1;
    }

    public static void main(String[] args){
        No34_SearchRange test=new No34_SearchRange();
        int[] nums={1,2,5,7,8,8,9,10};
        int target=8;
        int[] ints = test.searchRange(nums, target);
        CommonUtil.display(ints);

        int[] ints1 = test.searchRange2(nums, target);
        CommonUtil.display(ints1);
    }
}
