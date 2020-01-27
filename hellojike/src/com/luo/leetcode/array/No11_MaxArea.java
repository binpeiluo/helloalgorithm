package com.luo.leetcode.array;

/*
    11. 盛最多水的容器
    给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
    在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        说明：你不能倾斜容器，且 n 的值至少为 2。
        示例:
        输入: [1,8,6,2,5,4,8,3,7]
        输出: 49
*/
public class No11_MaxArea {

    /**
     * 思路:
     *      该题所求的是由 height[i]和height[j]两条线段组成的矩形的最大面积
     *      暴力解法.两次for循环
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result=0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int calc=(j-i)*Math.min(height[i],height[j]);
                result=Math.max(result,calc);
            }
        }
        return result;
    }

    /**
     * 思路:
     *      经典解法,使用双指针.每次移动高度较低的元素指针
     *
     *      时间复杂度:  O(n)
     *      空间复杂度:  O(1)
     * @param height
     * @return
     */
    public int maxArea2(int[] height){
        int result=0;
        int lo=0,hi=height.length-1;
        while(lo<hi){
            result=Math.max(result,(hi-lo)*Math.min(height[lo],height[hi]));
            if(height[lo]<height[hi]){
                lo++;
            }else{
                hi--;
            }
        }
        return result;

    }

    public static void main(String[] args){
        No11_MaxArea test=new No11_MaxArea();
        int[] nums={1,8,6,2,5,4,8,3,7};
        int i = test.maxArea2(nums);
        System.out.println(i);
    }
}
