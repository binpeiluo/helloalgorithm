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

    /**
     * 由于有重复元素,首选对数组进行排序,然后使用visited数组辅助判断
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        boolean[] visited=new boolean[nums.length];
        helper2(result,new ArrayList<>(),visited,nums);
        return result;
    }

    private void helper2(List<List<Integer>> result,List<Integer> currSelected,
                         boolean[] visited,int[] nums){
        if(currSelected.size()==nums.length){
            result.add(new ArrayList<>(currSelected));
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            //正是因为刚被撤销，下面的搜索中还会使用到，因此会产生重复，剪掉的就应该是这样的分支
            if((i>0&&nums[i]==nums[i-1])&&!visited[i-1])
                continue;
            if(visited[i])
                continue;
            currSelected.add(nums[i]);
            visited[i]=true;
            helper2(result,currSelected,visited,nums);
            currSelected.remove(currSelected.size()-1);
            visited[i]=false;

        }
    }

    public static void main(String[] args){
        No47_PermuteUnique test=new No47_PermuteUnique();
//        int[] nums={1,1,1,2,2,2};
        int[] nums={1,1,2,3};
        List<List<Integer>> lists = test.permuteUnique(nums);
        System.out.println(lists);
        System.out.println(lists.size());

        List<List<Integer>> lists1 = test.permuteUnique2(nums);
        System.out.println(lists1);
    }
}
