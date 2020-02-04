package com.luo.leetcode.array;

/*
    35. 搜索插入位置
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
        你可以假设数组中无重复元素。
        示例 1:
        输入: [1,3,5,6], 5
        输出: 2
        示例 2:
        输入: [1,3,5,6], 2
        输出: 1
        示例 3:
        输入: [1,3,5,6], 7
        输出: 4
        示例 4:
        输入: [1,3,5,6], 0
        输出: 0
*/
public class No35_SearchInsert {

    /**
     * 思路:
     *      使用双指针,参照labuladong关于二分的说明.
     *      控制left和right双指针不断缩小直至left=小于target的元素的个数时.正好指向第一个大于等于target的元素
     *      该指针就是插入的位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if(nums.length<1)
            return 0;
        int left=0;
        int right=nums.length;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid;
            else if(nums[mid]==target)
                right=mid;
        }
        return left;
    }

    public static void main(String[] args){
        No35_SearchInsert test=new No35_SearchInsert();
        int[] nums={10};
        int target=8;
        int i = test.searchInsert(nums, target);
        System.out.println(i);

    }
}
