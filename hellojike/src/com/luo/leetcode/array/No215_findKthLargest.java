package com.luo.leetcode.array;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 */
public class No215_findKthLargest {

    /**
     * 自己想到两种思路
     *  第一,使用堆
     *  第二,使用类似快排的partition方法.当发现切分后切分元素的位置小于k则在切分后的右数组中再次切分
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(k,(n1,n2)->n1-n2);
        for(int n:nums){
            queue.offer(n);
            if(queue.size()>k)
                queue.poll();
        }
        return queue.poll();
    }

    public static void main(String[] args){
        No215_findKthLargest test=new No215_findKthLargest();
//        int[] nums={3,2,3,1,2,4,5,5,6};
//        int k=4;
        int[] nums={3,2,1,5,6,4};
        int k=2;
        int kthLargest = test.findKthLargest(nums, k);
        System.out.println(kthLargest);
    }
}
