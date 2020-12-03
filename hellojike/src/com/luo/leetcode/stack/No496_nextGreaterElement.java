package com.luo.leetcode.stack;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 单调栈系列之1
 * 496. 下一个更大元素 I
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 *
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 */
public class No496_nextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resultFor2=nextGreaterElement(nums2);
        int[] result=new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num1=nums1[i];
            int index2=0;
            while(nums2[index2]!=num1){
                index2++;
            }
            result[i]=resultFor2[index2];
        }
        return result;
    }

    private int[] nextGreaterElement(int[] nums){
        int len=nums.length;
        int[] result=new int[len];
//        单调栈 单调递增
        Stack<Integer> stack=new Stack();
        for (int i=len-1;i>=0;i--){
//            当前元素
            int n=nums[i];
//            如果当前元素比单调栈头大，则讲头部的元素弹出，因为该元素将会挡住他们
            while(!stack.isEmpty()&&n>=stack.peek()){
                stack.pop();
            }
//            该元素的后面一个更大的元素此时就可以通过单调栈获取
            result[i]=stack.isEmpty()?-1:stack.peek();
//            将该元素压入栈中
            stack.push(n);
        }
        return result;
    }

    public static void main(String[] args) {
        No496_nextGreaterElement test=new No496_nextGreaterElement();
//        int[] nums1 = {4,1,2};
//        int[] nums2 = {1,3,4,2};

        int[] nums1 = {2,4};
        int[] nums2 = {1,2,3,4};

        int[] ints = test.nextGreaterElement(nums1, nums2);
        CommonUtil.display(ints);
    }
}
