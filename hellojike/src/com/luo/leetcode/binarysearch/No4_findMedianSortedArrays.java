package com.luo.leetcode.binarysearch;

import com.luo.util.CommonUtil;

/*

4. 寻找两个正序数组的中位数
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。
示例 1:
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0
示例 2:
nums1 = [1, 2]
nums2 = [3, 4]
则中位数是 (2 + 3)/2 = 2.5
*/
public class No4_findMedianSortedArrays {

    /**
     * 思路1
     *      使用一个临时数组,然后归并排序,找出中位数
     * 思路2
     *      使用两个指针,分别在数组1的起始位置和数组2的起始位置.然后判断两个指针大小,每次排除一个元素.
     *      当经过 (len1+len2)/2 次循环之后即可找出中位数

     * 思路3
     *      网友提供
     *      思路2只是每次排除一个元素,可以对k二分.
     *      比较两个数组的第k/2个元素,据此排除k/2个元素
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left)
                + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }
    //i: nums1的起始位置 j: nums2的起始位置
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }

    public double findMedianSortedArrays2(int[] nums1,int[] nums2){
        int len1=nums1.length;
        int len2=nums2.length;
        int left=(len1+len2+1)/2;
        int right=(len1+len2+2)/2;

        return (helper2(nums1, 0, nums2, 0, left)
                + helper2(nums1, 0, nums2, 0, right)) / 2.0;

    }

    /**
     * 在nums1起始位置start1开始和nums2起始位置start2开始寻找第k小的数组
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param k
     * @return
     */
    private int helper2(int[] nums1,int i,int[] nums2,int j,int k){
//        当数组nums1起始位置越界时,取nums2数组的元素
        if(i>=nums1.length)
            return nums2[j+k-1];
        if(j>= nums2.length)
            return nums1[i+k-1];
//        当取值为第0个表示,当前最小值
        if(k==1)
            return Math.min(nums1[i],nums2[j]);
//        当某个数组大小没有k/2个时,
        int midVal1=(i+k/2-1<nums1.length)?nums1[i+k/2-1]:Integer.MAX_VALUE;
        int midVal2=(j+k/2-1<nums2.length)?nums2[j+k/2-1]:Integer.MAX_VALUE;

        if(midVal1 < midVal2){
            return helper2(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return helper2(nums1, i, nums2, j + k / 2 , k - k / 2);
        }

    }

    public static void main(String[] args){
        No4_findMedianSortedArrays test=new No4_findMedianSortedArrays();
//        int[] nums1={1,2};
//        int[] nums2={3,4,5,6,7};
        int[] nums1={3};
        int[] nums2={1,2,4};

        double medianSortedArrays = test.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);

        double medianSortedArrays2 = test.findMedianSortedArrays2(nums1, nums2);
        System.out.println(medianSortedArrays2);
    }
}
