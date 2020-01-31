package com.luo.leetcode.array;

/*
    33. 搜索旋转排序数组
    假设按照升序排序的数组在预先未知的某个点上进行了旋转。
        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
        搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
        你可以假设数组中不存在重复的元素。
        你的算法时间复杂度必须是 O(log n) 级别。
        示例 1:
        输入: nums = [4,5,6,7,0,1,2], target = 0
        输出: 4
        示例 2:
        输入: nums = [4,5,6,7,0,1,2], target = 3
        输出: -1
*/
public class No33_Search {

    /**
     * 什么脑袋  我真是猪!
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length==0)
            return -1;
        int lo=0,hi=nums.length;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target)
                return mid;
//          中间的元素小于target

            else if(nums[mid]<target){
//                后边是递增的    两次判断都需要判断前边有序还是后边有序,所以可以直接将这个判断条件拿出来
                if(nums[mid]<nums[hi]){
                    if(target<nums[hi])
                        lo=mid+1;
                    else
                        hi=mid-1;
                }else{//前面是递增的
                    if(nums[mid]>target)
                        hi=mid-1;
                    else
                        lo=mid+1;
                }
            }else{//中间元素大于target
                if(nums[mid]<nums[hi]){//后边是递增的
                    if(target<nums[hi])
                        lo=mid+1;
                    else
                        hi=mid-1;
                }else{//前面是递增的
//                    if(target)
                }
            }
        }
        return lo;
    }

    /**
     * leetcode大神提供的思路.牛皮
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target){
        if(nums.length<1)
            return -1;
        int lo=0,hi=nums.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[lo]<=nums[mid]){//前边有序  注意这里是<=,这是因为当区间较小时,lo等于mid
                if(nums[lo]<=target&&target<nums[mid])//注意这里等号的使用
                    hi=mid-1;
                else
                    lo=mid+1;
            }else {//后边有序
                if(nums[mid]<target&&target<=nums[hi])
                    lo=mid+1;
                else
                    hi=mid-1;
            }
        }
        return -1;
    }


    public static void main(String[] args){
        No33_Search test=new No33_Search();
        int[] nums={4,5,6,7,0,1,2};
        int target=3;
        int i = test.search2(nums, target);
        System.out.println(i);
    }
}
