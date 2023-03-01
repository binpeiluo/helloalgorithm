package com.luo.leetcode.doublepointer;


/**
 * 面试题 17.21. 直方图的水量
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 */
public class No_m_1721_trap {

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result=0;
        int len=height.length;
        int[] leftMax=new int[len];
        int[] rightMax=new int[len];
        for (int i = 0; i < len; i++) {
            leftMax[i]=Math.max(height[i],i==0?0:leftMax[i-1]);
            rightMax[len-1-i]=Math.max(height[len-1-i],i==0?0:rightMax[len-i]);
        }
        for (int i = 0; i < len; i++) {
            result+=Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return result;
    }

    /**
     * 单调栈
     * 从左到右遍历数组,维持单调栈(栈内储存元素角标).使栈底到栈顶对应的元素递减
     * 如此遍历到第三个元素时便可以开始计算储存水的大小,当height[i]>height[top]便可以计算储水的大小
     * 宽度为 height[left]
     * @param heigh
     * @return
     */
    public int trap2(int heigh){
        return 1;
    }

    public static void main(String[] args) {
        No_m_1721_trap test=new No_m_1721_trap();
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};

        int trap = test.trap(height);
        System.out.println(trap);
    }
}
