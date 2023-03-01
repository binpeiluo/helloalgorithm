package com.luo.leetcode.math;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 */
public class No62_uniquePaths {
    /**
     * 仔细想想其实是数学题
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int a=m+n-2;
        int b=n-1;
        long res=1;
        for(int i=0;i<b;i++){
            res*=a--;
        }
        for(int i=b;i>1;i--){
            res/=i;
        }
        return (int)res;
    }

    public int uniquePaths2(int m, int n){
        int a=m+n-2;
        int b=n-1;
        double res=1;
        for (int i = b; i >=1 ; i--) {
            res=res*a/i;
            a--;
        }
        return (int)res;
    }

    public static void main(String[] args){
        No62_uniquePaths test=new No62_uniquePaths();
        int i = test.uniquePaths(10, 10);
        System.out.println(i);

        int i1 = test.uniquePaths2(10, 10);
        System.out.println(i1);
    }
}
