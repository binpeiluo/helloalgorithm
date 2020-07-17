package com.luo.leetcode.binarysearch;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 */
@SuppressWarnings("Duplicates")
public class No35_searchInsert {

    public int searchInsert(int[] nums, int target) {
        int len=nums.length;
        int left=0,right=len;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid;
            }
        }
        return right;
    }

    public static void main(String[] args){
        No35_searchInsert test=new No35_searchInsert();
        int[] nums={1,3,5,6};
        int target=8 ;

        int i = test.searchInsert(nums, target);
        System.out.println(i);
    }
}
