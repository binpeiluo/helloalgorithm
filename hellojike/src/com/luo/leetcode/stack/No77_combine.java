package com.luo.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class No77_combine {

    /**
     * 典型数遍历,回溯问题
     * n限制遍历数的宽度,k限制遍历树的高度.并且需要避免访问顺序不同导致重复组合问题
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result=new ArrayList<>();
        helper(result,new ArrayList<>(),1,n,k);
        return result;
    }

    /**
     * 遍历树
     *  结束条件在树的底部,高度为2时
     * @param result
     * @param currSelected
     * @param n
     * @param k
     */
    private void helper(List<List<Integer>> result,List<Integer> currSelected,
                        int start,int n,int k){
        if(currSelected.size()==k){
            result.add(new ArrayList<>(currSelected));
            return;
        }
        for (int i = start; i<=n; i++) {
            currSelected.add(i);
            helper(result,currSelected,i+1,n,k);
            currSelected.remove(currSelected.size()-1);
        }
    }

    public static void main(String[] args){
        No77_combine test=new No77_combine();
        int n=4,k=2;
        List<List<Integer>> combine = test.combine(n, k);
        System.out.println(combine);
    }

}
