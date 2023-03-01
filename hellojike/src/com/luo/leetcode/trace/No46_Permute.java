package com.luo.leetcode.trace;

/*
    46. 全排列
    给定一个没有重复数字的序列，返回其所有可能的全排列。
        示例:
        输入: [1,2,3]
        输出:
        [
            [1,2,3],
            [1,3,2],
            [2,1,3],
            [2,3,1],
            [3,1,2],
            [3,2,1]
        ]
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class No46_Permute {

    /**
     * 思路:
     *      回溯算法.考虑对于数组nums,如何构建搜索树
     *      终止条件是当前列表的元素个数等于nums数组的个数,考虑如何剪枝,每次只加入没有被访问过的数据
     *
     *      一种思路是使用 list.constains 判断是否已经包含该元素
     *      一种思路是使用 一个额外的数组visited代表i元素是否被访问
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        Stack<Integer> curr=new Stack<>();
        helper(result,nums,curr);
        return result;
    }

    private void helper(List<List<Integer>> result, int[] nums,Stack<Integer> curr){
        if(curr.size()==nums.length){
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int num:nums){
            if(!curr.contains(num)){
                curr.push(num);
                helper(result,nums,curr);
                curr.pop();
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[nums.length];
        helper2(result,nums,visited,stack);
        return result;
    }

    private void helper2(List<List<Integer>> result,int[] nums,boolean[] visited,Stack<Integer> stack){
        if(stack.size()==nums.length){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]){
                stack.add(nums[i]);
                visited[i]=true;
                helper2(result,nums,visited,stack);
                stack.pop();
                visited[i]=false;
            }
        }
    }

    public static void main(String[] args){
        No46_Permute test=new No46_Permute();
        int[] nums={1,2,3};
        List<List<Integer>> permute = test.permute(nums);
        System.out.println(permute);

        List<List<Integer>> lists = test.permute2(nums);
        System.out.println(lists);
    }
}
