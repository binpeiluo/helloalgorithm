package com.luo.leetcode.dataconstruct;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 数组可以很容易计算中位数,但是添加元素则需要移动元素
 * 链表可以比较方便添加元素,但是计算中位数比较麻烦
 * 二叉树,java中的TreeSet就是使用该结构实现的,但是set元素不会重复,而且不能很方便获取中位数
 * 堆 对顶最大或者最小,貌似也不行.
 *
 * 使用两个堆(优先队列),将列表数据切分放入到这两个堆中.
 * 一个大堆顶(堆顶元素最大,整体元素都是偏小的)
 * 一个小堆顶(堆顶元素最小,整体元素都是偏大的)
 *
 * 并且维护两个堆的元素之差不超过1,大顶堆的最大元素不超过小顶堆的最小元素
 *
 * 此时获取中位数就是或者数量多的一个堆的堆顶或者两个堆顶的平均数
 * 添加一个元素就是往多的元素的堆添加,然后该堆弹出一个元素释放到另一个堆
 */
public class No295_MedianFinder {

    static class MedianFinder{

        /*大顶堆 倒序*/
        private PriorityQueue<Integer> small;
        /*小顶堆 正序*/
        private PriorityQueue<Integer> large;

        /** initialize your data structure here. */
        public MedianFinder() {
            small=new PriorityQueue<>((a,b)->b-a);
            large=new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(small.size()>=large.size()){
                small.offer(num);
                large.offer(small.poll());
            }else{
                large.offer(num);
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if(small.size()>large.size()){
                return small.peek();
            }else if(small.size()<large.size()){
                return large.peek();
            }else{
                return (small.peek()+large.peek())/2.0;
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder test=new MedianFinder();
        test.addNum(1);
        test.addNum(2);
        double median = test.findMedian();
        System.out.println(median);
        test.addNum(3);
        median = test.findMedian();
        System.out.println(median);
    }
}
