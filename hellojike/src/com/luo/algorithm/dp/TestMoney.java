package com.luo.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 凑零钱问题
 */
public class TestMoney {
    /**
     *  给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck ，每种硬
     * 币的数量⽆限，再给⼀个总⾦额 amount ，问你最少需要⼏枚硬币凑出这个
     * ⾦额，如果不可能凑出，算法返回 -1 。
     *
     * 该问题符合最优子结构性质.即找到凑齐10元的零钱最少个数,可以得知凑齐11元则可以通过凑齐10元+1元硬币
     * @param moneys
     * @param sum
     * @return
     */
    public int coinChange(int[] moneys,int sum){
//        1 明确状态.原问题和子问题之间变化的变量.由于每种零钱的个数不限,故变量只有金额值

//        2 定义DP数组定义:定义DP数组,当前目标金额是n,至少需要dp[n]个硬币凑齐

//        3 明确选择并且择优.也就是对于每个状态(金额),做出什么选择改变当前状态.
//          在本例子中是无论当前金额多少,从硬币列表中选择一种硬币,然后当前金额减少

//        4 明确base case.当目标金额为0时,需要的硬币个数为0.当目标金额小于0时,无解

        int dp = dp(moneys, sum);
        return dp;
    }

    private int dp(int[] monenys,int n){
        if(n==0)
            return 0;
        if(n<0)
            return -1;
        int res=Integer.MAX_VALUE;
        for (int money:monenys){
            int item = dp(monenys, n - money);
            if(item!=-1)
                res=Math.min(res,item+1);
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    /**
     * 这种问题也可以使用回溯解决
     * 回溯适合找出所有可能
     * @param moneys
     * @param sum
     * @return
     */
    public int coinChange2(int[] moneys,int sum){
//        选择列表,做出选择,回溯选择
        List<Stack<Integer>> result=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        helper(moneys,sum,result,stack);
        if(result.isEmpty())
            return -1;
        else{
            int min=Integer.MAX_VALUE;
            for (Stack<Integer> s:result){
                min=Math.min(min,s.size());
            }
            return min;
        }
    }

    private void helper(int[] moneys, int target, List<Stack<Integer>> result, Stack<Integer> stack){
        if(target==0){
            Stack<Integer> s=new Stack<>();
            for(Integer i:stack)
                s.push(i);
            result.add(s);
            return;
        }
        for (int money:moneys){
            if(target-money>=0){
                stack.push(money);
                helper(moneys,target-money,result,stack);
                stack.pop();
            }
        }
    }

    public static void main(String[] args){
        TestMoney test=new TestMoney();
        int[] moneys={2,4,6};
        int sum=11;
        int i = test.coinChange(moneys, sum);
        System.out.println(i);

        int i1 = test.coinChange2(moneys, sum);
        System.out.println(i1);
    }
}
