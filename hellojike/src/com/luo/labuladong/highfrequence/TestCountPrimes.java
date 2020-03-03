package com.luo.labuladong.highfrequence;

import java.util.Arrays;

/**
 * 高频之计算素数个数
 */
public class TestCountPrimes {
    /**
     *
     *
     * @param n 自然数
     * @return  返回不大于该自然数的素数
     */
    public int countPrimes(int n){
        boolean[] helper=new boolean[n+1];
//        Arrays.fill(helper,true);

        for (int i = 2; i*i<n; i++) {
            if(!helper[i]){
                for (int j = 2*i; j <=n; j+=i) {
                    helper[j]=true;
                }
            }
        }

        int res=0;
        for (int i = 2; i <=n; i++) {
            if(!helper[i])
                res++;
        }
        return res;
    }

    public static void main(String[] args){
        TestCountPrimes test=new TestCountPrimes();
        int i = test.countPrimes(100000000);
        System.out.println(i);
    }
}
