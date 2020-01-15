package com.luo.sevendays.day2.queue;

import com.luo.util.CommonUtil;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class MaxSlidingWindow {
    /**
     * 暴力破解法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null)
            return null;
        int[] res=new int[nums.length-k+1];
        for(int i=0;i<res.length;i++){
            int max=i;
            for(int p=i;p<i+k;p++){
                if(nums[p]>nums[max])
                    max=p;
            }
            res[i]=nums[max];
        }
        return res;
    }

    /**
     * 使用优先队列
     * 优先队列,删除插入复杂度为 O(logn)
     * 故时间复杂度为 O(nlogk),空间复杂度为O(n)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowPriority(int[] nums,int k){
        if(nums==null)
            return null;
        PriorityQueue<Integer> queue=new PriorityQueue<>(3,(e1,e2)->e2-e1);
        for(int i=0;i<k;i++){
            queue.add(nums[i]);
        }
        int[] res=new int[nums.length-k+1];
        for(int i=0;i<res.length;i++){
            res[i]=queue.peek();
            if(i!=res.length-1){
                queue.remove(nums[i]);
                queue.add(nums[k+i]);
            }
        }
        return res;
    }

    /**
     * 使用双端队列
     * 实现比优先队列复杂,但是双端队列出队和入队的复杂度只有O(1)
     * 该算法时间复杂度为O(n),空间复杂度为O(n)
     * @param nums
     * @param k
     * @return
     */
    int[] maxSlidingWindowDeque(int[] nums,int k){
        ArrayDeque<Integer> deque=new ArrayDeque<>();
        int[] res=new int[nums.length-k+1];
        int maxIndex=0;
        for(int i=0;i<k;i++){
            deque.addLast(i);
            if(nums[i]>nums[maxIndex])
                maxIndex=i;
        }
        res[0]=nums[maxIndex];
        for(int i=1;i<res.length;i++){
            if(!deque.isEmpty()&&deque.getFirst()==i-1)
                deque.removeFirst();
            while(!deque.isEmpty()&&nums[deque.getFirst()]<nums[k+i-1])
                deque.removeFirst();
            deque.addLast(k+i-1);
            res[i]=nums[deque.getFirst()];
        }
        return res;
    }

    public static void main(String[] args){
        MaxSlidingWindow test=new MaxSlidingWindow();
        int[] nums = new int[]{1,3,-1,-10,-3,5,3,6,7};
        int k = 3;
        int[] ints = test.maxSlidingWindowPriority(nums, k);
        CommonUtil.display(ints);
    }
}
