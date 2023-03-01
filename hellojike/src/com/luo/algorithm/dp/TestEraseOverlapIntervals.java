package com.luo.algorithm.dp;

import java.util.Arrays;

/**
 * 动态规划 贪心问题
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 */
public class TestEraseOverlapIntervals {
    /**
     * 典型的贪心问题,找到没有重叠的区间的最大数,即可得到结果
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(i1,i2)->{
            return i1[1]-i2[1];
        });
        int len=intervals.length;
        int res=0;
        for (int i = 0; i < len; i++) {
            int[] curr=intervals[i];
            while(i+1<len&&intervals[i+1][0]<curr[1]){
                i++;
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        TestEraseOverlapIntervals test=new TestEraseOverlapIntervals();
        int[][] intvs={ {1,2}, {2,3}, {3,4}, {1,3} };
        int i = test.eraseOverlapIntervals(intvs);
        System.out.println(i);
    }
}
