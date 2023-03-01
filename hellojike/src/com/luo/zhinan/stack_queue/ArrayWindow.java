package com.luo.zhinan.stack_queue;

import com.luo.util.CommonUtil;

import java.util.ArrayDeque;
import java.util.List;

/**
 * 生成窗口的最大值数组
 */
public class ArrayWindow {

    /**
     * 可以使用滑动窗口
     * 使用双端队列, 滑动窗口时对双端队列进行操作
     * 双端队列保存元素索引, 一段保存当前窗口最大值索引, 维持单调递减
     *      当滑动窗口时, 需要考虑有效元素入队列和过期元素出队列
     *      遍历到 a[i], 判断与队列尾对应元素j的大小
     *          入队列操作
     *              如果a[i] >= a[j], 那么弹出队列直到 a[i] 大于队列尾元素
     *              如果a[i] < a[j], 那么直接压入队列尾
     *          出队列操作
     *              如果队列头元素索引过期, 那么弹出队列头. i>=j+3则弹出
     * @param a
     * @param n
     * @return
     */
    public int[] arrayWindow(int[] a, int n){
        int[] result = new int[a.length-n+1];
        ArrayDeque<Integer> help = new ArrayDeque<>();
        for(int i=0;i<a.length;i++){
            while(!help.isEmpty() && a[i]>=a[help.peekLast()]){
                help.pollLast();
            }
            help.addLast(i);
            if(i>=help.peekFirst()+3){
                help.pollFirst();
            }
            // 注意这里不是判断 i>=n, 第一次写入的时候是在i=n-1的时候
            if(i>=n-1){
                result[i-n+1]=a[help.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayWindow service = new ArrayWindow();
        int[] a = {4,3,5,4,3,3,6,7};
        int n = 3;
        int[] result = service.arrayWindow(a, n);
        CommonUtil.display(result);
    }
}
