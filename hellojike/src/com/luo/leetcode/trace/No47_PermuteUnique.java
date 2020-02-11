package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
    47. 全排列 II
    给定一个可包含重复数字的序列，返回所有不重复的全排列。
        示例:
        输入: [1,1,2]
        输出:
        [
            [1,1,2],
            [1,2,1],
            [2,1,1]
        ]
*/
public class No47_PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[nums.length];
        helper(result,nums,visited,stack);
        return result;
    }

    private void helper(List<List<Integer>> result, int[] nums,boolean[] visited, Stack<Integer> stack){
        if(stack.size()==nums.length){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(i>0&&nums[i]==nums[i-1]&&!visited[i-1])
                continue;
            if(!visited[i]){
                stack.add(nums[i]);
                visited[i]=true;
                helper(result,nums,visited,stack);
                stack.pop();
                visited[i]=false;
            }
        }
    }

    public static void main(String[] args){
        No47_PermuteUnique test=new No47_PermuteUnique();
        int[] nums={1,1,1,2,2,2};
        List<List<Integer>> lists = test.permuteUnique(nums);
        System.out.println(lists);
        System.out.println(lists.size());
    }
}
