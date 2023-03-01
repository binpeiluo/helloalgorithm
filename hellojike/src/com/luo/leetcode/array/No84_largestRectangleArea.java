package com.luo.leetcode.array;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class No84_largestRectangleArea {

    /**
     * 最navie的想法
     * 暴力破解法,两次for循环代表左右边两个矩形,能凑起来的面积由最低的高度决定
     *
     * 时间复杂度:   O(n^3)
     * 空间复杂度:   O(1)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if(heights.length==0)
            return 0;
        int len=heights.length;
        int res=0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int minHeight=Integer.MAX_VALUE;
                for (int k = i; k <=j; k++) {
                    minHeight=Math.min(minHeight,heights[k]);
                }
                res=Math.max(minHeight*(j-i+1),res);
            }
        }
        return res;
    }

    /**
     * 优化暴力解法
     *
     * 时间复杂度:   O(n^2)
     * 空间复杂度:   O(1)
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights){
        if(heights.length==0)
            return 0;
        int len=heights.length;
        int res=0;
        for (int i = 0; i < len ; i++) {
            int minHeight=Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                minHeight=Math.min(minHeight,heights[j]);
                res=Math.max(res,minHeight*(j-i+1));
            }
        }
        return res;
    }

    /**
     * 分治
     *  思路:
     *      发现最大面积出现于下边三种情况
     *          1 发现最低高度后向两边延伸
     *          2 左边的最大面积(子问题)
     *          3 右边的最大面积(子问题)
     *
     * 时间复杂度:   O(nlogn)~O(n^2) 当高度数组为有序时,分治算法没有任何优化效果
     * 空间复杂度:   O(n)        递归需要的空间
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights){
        return helper3(heights,0,heights.length-1);
    }
    private int helper3(int[] heights,int start,int end){
        if(start>end)
            return -1;
        int minIndex=start;
        for (int i = start; i <=end; i++) {
            if(heights[minIndex]>heights[i])
                minIndex=i;
        }
        return Math.max(
                heights[minIndex]*(end-start+1),
                Math.max(
                        helper3(heights,start,minIndex-1),
                        helper3(heights,minIndex+1,end)
                )
        );
    }





    public static void main(String[] args){
        No84_largestRectangleArea test=new No84_largestRectangleArea();
        int[] height={2,1,5,6,2,3};
        int i = test.largestRectangleArea(height);
        System.out.println(i);

        int i1 = test.largestRectangleArea2(height);
        System.out.println(i1);

        int i2 = test.largestRectangleArea3(height);
        System.out.println(i2);
    }
}
