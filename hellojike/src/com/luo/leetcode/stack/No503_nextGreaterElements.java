package com.luo.leetcode.stack;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 单调栈系列之2
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 */
@SuppressWarnings("Duplicates")
public class No503_nextGreaterElements {

    /**
     * 该题目与496区别在于此题是处理循环数组
     * 比较容易想到的方法是将输入数组翻倍，变成两倍，这样子每个元素就能处理到
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int len=nums.length;
        int[] temp=new int[len*2];
        for (int i = 0; i < len; i++) {
            temp[i]=nums[i];
            temp[i+len]=nums[i];
        }
        int tempLen=temp.length;
        int[] resultTemp=new int[tempLen];

        Stack<Integer> stack=new Stack<>();
        for (int i = tempLen-1; i >=0 ; i--) {
            int num=temp[i];
            while(!stack.isEmpty()&&num>=stack.peek()){
                stack.pop();
            }
            resultTemp[i]=stack.isEmpty()?-1:stack.peek();
            stack.push(num);
        }
        int[] result=new int[len];
        for (int i = 0; i < len; i++) {
            result[i]=resultTemp[i];
        }
        return result;
    }

    /**
     * 模拟两倍数组
     * @param nums
     * @return
     */
    public int[] nextGreaterElements2(int[] nums) {
        int len=nums.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();
        for (int i = 2*len-1; i >=0 ; i--) {
            int ri=i%len;
            int num=nums[ri];
            while(!stack.isEmpty()&&num>=stack.peek()){
                stack.pop();
            }
            result[ri]=stack.isEmpty()?-1:stack.peek();
            stack.push(num);
        }
        return result;
    }

    public static void main(String[] args) {
        No503_nextGreaterElements test=new No503_nextGreaterElements();

        int[] nums={1,2,1};

        int[] ints = test.nextGreaterElements(nums);
        CommonUtil.display(ints);

        int[] ints1 = test.nextGreaterElements2(nums);
        CommonUtil.display(ints1);
    }
}
