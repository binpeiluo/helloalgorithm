package com.luo.labuladong.mind.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯系列
 * 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有组合
 * 比如输入 n = 4, k = 2，输出如下结果，顺序无所谓，但是不能包含重复（按照组合的定义，[1,2] 和 [2,1] 也算重复）
 *  [ [1,2], [1,3], [1,4], [2,3], [2,4], [3,4] ]
 */
public class TestCombine {

    /**
     * 回溯框架
     * 由于不能重复,那么也使用start限制取值
     * 这也是典型的回溯算法，k 限制了树的高度，n 限制了树的宽度
     * 这里的函数和计算子集的差不多，区别在于，更新 result 的时机是树到达底端时。
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n,int k) {
        List<List<Integer>> result=new ArrayList<>();
        helper(result,new ArrayList<>(),1,n,k);
        return result;
    }

    private void helper(List<List<Integer>> result,List<Integer> currSelected,
                        int start,int n,int k){
        if(currSelected.size()==k){
            result.add(new ArrayList<>(currSelected));
            return;
        }
        for (int i = start; i <=n; i++) {
            currSelected.add(i);
            helper(result,currSelected,i+1,n,k);
            currSelected.remove(currSelected.size()-1);
        }
    }

    public static void main(String[] args){
        TestCombine test=new TestCombine();
        int n=4;
        int k=2;
        List<List<Integer>> combine = test.combine(n, k);
        System.out.println(combine);
    }


}
