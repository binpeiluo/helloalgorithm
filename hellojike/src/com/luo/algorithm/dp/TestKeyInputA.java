package com.luo.algorithm.dp;

import com.luo.util.CommonUtil;

/**
 * 651.4键键盘
 *
 */
public class TestKeyInputA {

    /**
     *
     * @param n
     * @return
     */
    public int maxA(int n){
//        状态,   当前剩下键入的次数,当前A字符的数量,当前copy缓冲区字符数量
//        选择,   1键入A    2全选+复制(全选和复制是一起的)   3粘贴
        return dp(n,0,0);
    }

    private int dp(int n,int aNum,int copyNum){
        if(n<0)
            return Integer.MIN_VALUE;
        if(n==0)
            return aNum;
        int c1=dp(n-1,aNum+1,copyNum);
        int c2=dp(n-2,aNum,aNum);
        int c3=dp(n-1,aNum+copyNum,copyNum);
        int max = CommonUtil.max(c1, c2, c3);
        return max;
    }

    public int maxA2(int n){
//        定义状态,键入的次数
//        定义选择,键入A或者ctrl+v. ctrl+v之前必定是ctrl+a,ctrl+c
        int[] dp=new int[n+1];
        for (int i = 1; i <= n; i++) {
//            键入A
            int m=dp[i-1]+1;
//            ctrl+v,假设是在j处开始键入ctrl+v
            for (int j = 2; j < i; j++) {
                m=Math.max(m,dp[j-2]*(i-j+1));
            }
            dp[i]=m;
        }
        return dp[n];
    }

    public static void main(String[] args){
        TestKeyInputA test=new TestKeyInputA();
        int num=20;

        int i1 = test.maxA2(num);
        System.out.println(i1);

        int i = test.maxA(num);
        System.out.println(i);
    }
}
