package com.luo.leetcode.array;

/*
    42. 接雨水
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
        上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
        示例:
        输入: [0,1,0,2,1,0,1,3,2,1,2,1]
        输出: 6
*/

public class No42_Trap {

    /**
     * 思路:
     *      使用最简单的思路.一行一行的计算
     *      差点超时
     *
     *      时间复杂度:  O(n^2)
     *      空间复杂度:  O(1)
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height==null||height.length<=2)
            return 0;
        int max=height[0];
//          找到最大值
        for(int h:height){
            max=Math.max(max,h);
        }
        int res=0;
        for (int i = 1; i <= max; i++) {
            res+=calcColumn(height,i);
        }
        return res;
    }

    private int calcColumn(int[] height,int c){
        int left=0,right=height.length-1;
        int sum=0;
        while(left<right&&height[left]<c)
            left++;
        while(right>left&&height[right]<c){
            right--;
        }
        if(left==right||(left+1==right))
            return 0;
        else{
            for (int i = left+1; i < right; i++) {
                if(height[i]<c)
                    sum++;
            }
        }
        return sum;
    }

    /**
     * 使用两个辅助数组记录两边的最大值
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if(height.length<=2)
            return 0;
        int len=height.length;
        int[] leftMax=new int[len];
        int[] rightMax=new int[len];
        for (int i = 0; i < len; i++) {
            leftMax[i]=Math.max(i==0?leftMax[i]:leftMax[i-1],height[i]);
            rightMax[len-1-i]=Math.max(i==0?rightMax[len-1-i]:rightMax[len-i],height[len-1-i]);
        }
        int res=0;
        for (int i = 1; i < len-1; i++) {
            res+=Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return res;
    }


    public static void main(String[] args){
        No42_Trap test=new No42_Trap();
        int[] height={2,0,2};
        int trap = test.trap(height);
        System.out.println(trap);

        int i = test.trap2(height);
        System.out.println(i);
    }
}
