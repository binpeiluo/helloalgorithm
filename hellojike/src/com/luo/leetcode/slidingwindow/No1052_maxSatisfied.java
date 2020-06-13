package com.luo.leetcode.slidingwindow;
/*
1052. 爱生气的书店老板
今天，书店老板有一家店打算试营业 customers.length 分钟。
每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
示例：
输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
*/

public class No1052_maxSatisfied {


    /**
     * 滑动窗口法
     *  首先将老板不生成的时间的用户累计下来,然后再从剩下的用户列表中找到窗口里的最大值
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int result=0;
        int len=customers.length;
        for (int i = 0; i < len; i++) {
            if(grumpy[i]==0){
                result+=customers[i];
                customers[i]=0;
            }
        }
        int j=0,max=0,window=0;
        for (int i = 0; i < len; i++) {
            if(i<X){
                window+=customers[i];
                max=window;
                continue;
            }else{
                window-=customers[j++];
                window+=customers[i];
                max=Math.max(max,window);
            }
        }
        return result+max;
    }

    public static void main(String[] args){
        No1052_maxSatisfied test=new No1052_maxSatisfied();
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int X = 3;

        int i = test.maxSatisfied(customers, grumpy, X);
        System.out.println(i);
    }
}
