package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class No90_subsetsWithDup {

    /**
     * 类似于子集78题
     * 区别在于有重复数据,可以使用类似的方法
     * 先对入参进行排序
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        boolean[] visited=new boolean[nums.length];
        helper(result,new ArrayList<>(),nums,visited,0);
        return result;
    }

    private void helper(List<List<Integer>> result,List<Integer> currSelected,
                        int[] nums,boolean[] visited ,int start){
        result.add(new ArrayList<>(currSelected));
        for (int i = start; i < nums.length; i++) {
            if(i>0 && nums[i-1]==nums[i]
                    && !visited[i-1]){
                continue;
            }
            currSelected.add(nums[i]);
            visited[i]=true;
            helper(result,currSelected,nums,visited,i+1);
            currSelected.remove(currSelected.size()-1);
            visited[i]=false;
        }
    }

    public static void main(String[] args){
        No90_subsetsWithDup test=new No90_subsetsWithDup();
//        int[] nums={1,1,2,2};

        int[] nums={5,5,5,5};

        List<List<Integer>> lists = test.subsetsWithDup(nums);
        System.out.println(lists);
    }
}
