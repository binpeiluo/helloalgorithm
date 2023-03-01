package com.luo.leetcode.math;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * 1 <= n <= 1690
 */
public class No264_nthUglyNumber {

    /**
     * 思路 使用最小堆储存1,然后依次将2x，3x，5x 同时避免数字重复
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if(n==1){
            return 1;
        }
        PriorityQueue<Long> queue=new PriorityQueue();
        HashSet<Long> exisit=new HashSet<>();
        queue.add(1L);
        exisit.add(1L);
        while(--n>=1){
            long poll=queue.poll();
            if(!exisit.contains(2*poll)){
                queue.add(2*poll);
                exisit.add(2*poll);
            }
            if(!exisit.contains(3*poll)){
                queue.add(3*poll);
                exisit.add(3*poll);
            }
            if(!exisit.contains(5*poll)){
                queue.add(5*poll);
                exisit.add(5*poll);
            }

        }
        long result=queue.peek();
        return (int)result;
    }

    public static void main(String[] args) {
        No264_nthUglyNumber test=new No264_nthUglyNumber();
        int i = test.nthUglyNumber(1407);
        System.out.println(i);
    }

}
