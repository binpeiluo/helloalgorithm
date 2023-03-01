package com.luo.leetcode.trace;

import java.util.*;

/*

    40. 组合总和 II
    给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的每个数字在每个组合中只能使用一次。
        说明：
        所有数字（包括目标数）都是正整数。
        解集不能包含重复的组合。 
        示例 1:
        输入: candidates = [10,1,2,7,6,1,5], target = 8,
        所求解集为:
        [
            [1, 7],
            [1, 2, 5],
            [2, 6],
            [1, 1, 6]
        ]
        示例 2:
        输入: candidates = [2,5,2,1,2], target = 5,
        所求解集为:
        [
             [1,2,2],
             [5]
        ]
*/
public class No40_CombinationSum2 {

    /**
     * 思路:
     *      这道题跟No39很类似
     *      No39 输出的数组没有重复,每个角标元素可以使用多次,答案组合不能重复
     *      No40 输入的数组可能重复,每个角标的元素只能使用一次,答案组合不能重复
     *
     *      1 对于没有重复元素的数组,由于回溯会将所有组合全部考虑一次,所以对于同一个组合的不同访问顺序会出现多个结果.想象一下树搜索
     *          这种问题可以通过对原数组进行排序,在一个节点的树的遍历过程只会访问该节点以及后边的元素
     *      2 对于有重复元素的数组,就算使用了(1)思维也无法避免类似cs=[2,2,6,6],target=8找出[[2,6],[2,6]]这种结果
     *          想想一下,在树的遍历过程.对于构成同一个target,有多个相同数值的元素可以选择,那么这将会导致树枝的重复.这部分是可以减去的
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        helper(result,candidates,target,0,stack);
        return result;
    }

    private void helper(List<List<Integer>> result, int[] candidates, int target, int start, Stack<Integer> stack){
        if(target==0){
            result.add(new ArrayList<>(stack));
            return;
        }
        for(int i=start;i<candidates.length&&target-candidates[i]>=0;i++){
            if(i>start&&candidates[i]==candidates[i-1])
                continue;
            stack.push(candidates[i]);
            helper(result,candidates,target-candidates[i],i+1,stack);
            stack.pop();
        }
    }

    public static void main(String[] args){
        No40_CombinationSum2 test=new No40_CombinationSum2();
        int[] candidates={10,1,2,7,6,1,5};
        int target=8;
        List<List<Integer>> lists = test.combinationSum2(candidates, target);
        System.out.println(lists);
    }
}
