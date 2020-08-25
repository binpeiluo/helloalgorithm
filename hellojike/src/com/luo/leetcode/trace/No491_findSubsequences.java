package com.luo.leetcode.trace;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，
 * 递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7],
 *  [6, 7, 7], [7,7], [4,7,7]]
 *
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 */
public class No491_findSubsequences {

    /**
     * 暴力算法可以使用二位数组代表是否选择当前元素来构建数组
     * 然后判断是否递增
     *
     * 这里使用回溯算法
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        Map<Integer,Integer> helper=new HashMap<>();
        for (int n:nums) {
            helper.putIfAbsent(n,1);
            helper.put(n,helper.get(n)+1);
        }
        for (int i = 0; i < nums.length; i++) {
            curr.clear();
            curr.add(nums[i]);
            helper.clear();
            trace(result,curr,nums,i,helper);
        }
        return result;
    }

    /**
     * 回溯
     * 使用 start 参数指定开始第一个元素
     * 但是相同数组会造成重复,比如 4,7   4,7
     * 可以通过构造一个hash 表示列表中可选数字中没有选择的数字
     * 比如当前发现到7这个数字,当发现前边已经有7这数字,而且没有选择时,可以不必选择
     * @param result
     * @param curr
     * @param nums
     * @param start
     */
    private void trace(
            List<List<Integer>> result,
            List<Integer> curr,
            int[] nums,
            int start,
            Map<Integer,Integer> helper){
//        终止条件是啥???
//        为什么不需要
//        因为不会无限制递归
        if(curr.size()>1){
            result.add(new ArrayList<>(curr));
        }
        for (int i = start+1,len=nums.length; i < len; i++) {
//            小于curr最后一个元素,则不考虑
            if(nums[i]<nums[start]){
                continue;
            }
            curr.add(nums[i]);
            trace(result,curr,nums,i,helper);
            curr.remove(curr.size()-1);
        }
    }

    public static void main(String[] args){
        No491_findSubsequences test=new No491_findSubsequences();
        int[] nums={4, 6, 7, 7};

        List<List<Integer>> subsequences = test.findSubsequences(nums);
        System.out.println(subsequences);

    }

}
