package com.luo.algorithm.dp;

import java.util.Arrays;

/**
 * 动态规划系列
 * 区间调度问题
 *
 * 贪心算法时动态规划中比较特殊的一种思维.
 * 贪心性质的问题则可以使用贪心算法,每一步做出的最优选择可以得到全局的最优选择
 *
 * 对于有贪心性质的问题
 */
public class TestIntervalSchedule {
    /**
     * 区间调度问题
     * 如何抽象该问题为贪心问题呢?
     * 每次在区间中选择最早结束的活动,然后把这个活动相交的活动排除.然后重复执行,每次选择都是最优
     * @param intvs
     * @return
     */
    public int intervalSchedule(int[][] intvs) {
        int len=intvs.length;
        Arrays.sort(intvs,(i1,i2)->{
            return i1[1]-i2[1];
        });
        int res=0;
        for (int i = 0; i < len; i++) {
            res++;
            int[] curr=intvs[i];
            while(i+1<len&&intvs[i+1][0]<curr[1]){
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        TestIntervalSchedule test=new TestIntervalSchedule();
        int[][] intvs={{1,2},{2,3},{3,4},{1,3}};
        int i = test.intervalSchedule(intvs);
        System.out.println(i);
    }
}
