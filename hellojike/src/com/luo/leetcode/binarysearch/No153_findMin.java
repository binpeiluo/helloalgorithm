package com.luo.leetcode.binarysearch;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *  
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 *  
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class No153_findMin {


    /**
     * e二分查找
     * 本题中输入的nums不会相同 所以其实判断分支中的=可以去掉
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
//        使用闭区间 跳出循环的条件为 left>right
        while(left<=right){
            int mid=left+(right-left)/2;
//            判断哪头是有序的 但是有可能一边的数组都是同一个值
            if(nums[left]<=nums[mid] && nums[mid]>nums[right]){
//                左边有序  右边升了再降
                left=mid+1;
            }else if(nums[left]>nums[mid] && nums[mid]<=nums[right]){
//                左边升了再降 右边有序
                right=mid;
            }else{
//                看不出来那边有序
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        No153_findMin test=new No153_findMin();

//        int[] nums={3,4,5,1,2};
//        int[] nums={4,5,6,7,0,1,2};
//        int[] nums={11,3};
        int[] nums={2,2,2,2,3};
        int min = test.findMin(nums);
        System.out.println(min);
    }
}
