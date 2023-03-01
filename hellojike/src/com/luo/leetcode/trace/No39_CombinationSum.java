package com.luo.leetcode.trace;

import java.util.*;

/*
    39. 组合总和
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的数字可以无限制重复被选取。
        说明：
        所有数字（包括 target）都是正整数。
        解集不能包含重复的组合。 
        示例 1:
        输入: candidates = [2,3,6,7], target = 7,
        所求解集为:
        [
            [7],
            [2,2,3]
        ]
        示例 2:
        输入: candidates = [2,3,5], target = 8,
        所求解集为:
        [
              [2,2,2,2],
              [2,3,3],
              [3,5]
        ]
*/
public class No39_CombinationSum {


    /**
     * 思路:
     *      做搜索,回溯的套路就是画图.代码其实就是根据画的图写出来的.
     *      参照 https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     *      思考此题如何画图
     *
     *      这种解法会导致重复解的问题,根据原因是在树遍历的时候同一个解调换了访问顺序.
     *      可以对数组进行排序解决
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> result=new HashSet<>();
        List<Integer> currList=new ArrayList<>();
        helper(result,candidates,target,currList);
        List<List<Integer>> l=new ArrayList(result);
        return l;
    }

    private void helper(Set<List<Integer>> result, int[] candidates, int target, List<Integer> currList){
        if(target==0){
            result.add(new ArrayList<>(currList));
            return;
        }
        for(int number:candidates){
            if(target-number>=0){
                currList.add(number);
                helper(result,candidates,target-number,currList);
                currList.remove((Object)number);
            }
        }
    }


    /**
     * 思路:
     *      跟前面的解法一致,只是添加了剪枝的步骤,把重复解过滤
     *      选择组合只能选择当前数字以及后边的元素,不可以挑选之前的元素.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result=new ArrayList<>();
        Stack<Integer> currList=new Stack<>();
        helper2(result,candidates,0,target,currList);
        return result;
    }

    private void helper2(List<List<Integer>> result,int[] candidates,int start,int target,Stack<Integer> currList){
        if(target==0){
            result.add(new ArrayList<>(currList));
            return;
        }
        for(int i=start;i<candidates.length&&target-candidates[i]>=0;i++){
            currList.push(candidates[i]);
            helper2(result,candidates,i,target-candidates[i],currList);
            currList.pop();
        }
    }

    public static void main(String[] args){
        No39_CombinationSum test=new No39_CombinationSum();
        int[] candidates={2,3,6,7};
        int target=7;
        List<List<Integer>> lists = test.combinationSum(candidates, target);
        System.out.println(lists);

        List<List<Integer>> lists1 = test.combinationSum2(candidates, target);
        System.out.println(lists1);
    }
}
