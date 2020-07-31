package com.luo.leetcode.binarysearch;


/**
 * 面试题 08.03. 魔术索引
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
 * 给定一个有序整数数组，编写一种方法找出魔术索引，
 * 若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
 * 若有多个魔术索引，返回索引值最小的一个。

 * 示例1:
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明: 0下标的元素为0
 * 示例2:
 *  输入：nums = [1, 1, 1]
 *  输出：1
 * 提示:
 * nums长度在[1, 1000000]之间
 */
public class No_m_08_03_findMagicIndex {

    /**
     * 这题是《程序员面试金典》里面的一道题。
     * 在书里这题是有两个小问的，它们的描述很相似。
     * 第二个小问相当于本题，而第一个小问保证了题目给定的数组是【严格单调递增】的，
     * 就是不会有重复的数字。
     *
     * 这里假设场景是严格单调递增
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
//        由于是严格单调递增,所有没有重复元素
//        那么有可能有一个魔术索引或者几个连续的魔术索引
//        这里可以使用二分查找,找出来

        int left=0,right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<mid){
//                其值小于索引,魔术索引在后边
                left=mid+1;
            }else if(nums[mid]>mid){
//                其值大于索引,魔术索引在前边
                right=mid;
            }else if(nums[mid]==mid){
//                其值等于索引,前边有可能还有
                right--;
            }
        }
        if(left==nums.length||nums[left]!=left){
            return -1;
        }
        return left;
    }


    public int findMagicIndex2(int[] nums) {
        return getAnswer(nums, 0, nums.length - 1);
    }

    public int getAnswer(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        }else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, right);
    }

    public static void main(String[] args){
        No_m_08_03_findMagicIndex test=new No_m_08_03_findMagicIndex();
        int[] nums={-1, 0, 2, 3, 4};

        int magicIndex = test.findMagicIndex(nums);
        System.out.println(magicIndex);
    }
}
