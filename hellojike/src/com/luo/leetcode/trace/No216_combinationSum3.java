package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class No216_combinationSum3 {

    /**
     * 经典回溯
     * k个数组成n
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
//        int[] number={9,8,7,6,5,4,3,2,1};
        int[] number={1,2,3,4,5,6,7,8,9};

        List<List<Integer>> result=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        trace(result,curr,number,0,k,n);
        return result;
    }

    private void trace(List<List<Integer>> result,
                       List<Integer> curr,
                       int[] number,
                       int index,
                       int k,int n){
        if(n==0 && k==0){
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i <number.length ; i++) {
            if(n<number[i] || k <=0){
                continue;
            }
            curr.add(number[i]);
            trace(result,curr,number,i+1,k-1,n-number[i]);
            curr.remove(curr.size()-1);
        }
    }

    public static void main(String[] args) {
        No216_combinationSum3 test=new No216_combinationSum3();
//        int k = 3, n = 7;
        int k = 3, n = 9;
        List<List<Integer>> lists = test.combinationSum3(k, n);
        System.out.println(lists);
    }
}
