package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯系列
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 */
public class No78_subsets {

    /**
     * 最先想到的是多次回溯解决,每次回溯从中找到树高为0~k的遍历,然后加入结果
     * 这种需要回溯多次,复杂度较高
     *
     * 另有想法,使用前序遍历,遍历到了顺便加上结果集.考虑如何剪枝
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        helper(result,new ArrayList<>(),0,nums);
        return result;
    }

    private void helper(List<List<Integer>> result,List<Integer> currSelected,int start,int[] nums){
        result.add(new ArrayList<>(currSelected));
        for (int i = start; i < nums.length; i++) {
            currSelected.add(nums[i]);
            helper(result,currSelected,i+1,nums);
            currSelected.remove(currSelected.size()-1);
        }
    }

    public static void main(String[] args){
        No78_subsets test=new No78_subsets();
        int[] nums={1,2,3};
        List<List<Integer>> subsets = test.subsets(nums);
        System.out.println(subsets);
    }
}
