package com.luo.leetcode.sort;

import com.luo.util.CommonUtil;

/*
75. 颜色分类
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
        此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
        注意:
        不能使用代码库中的排序函数来解决这道题。
        示例:
        输入: [2,0,2,1,1,0]
        输出: [0,0,1,1,2,2]

        进阶：
        一个直观的解决方案是使用计数排序的两趟扫描算法。
        首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
        你能想出一个仅使用常数空间的一趟扫描算法吗？
*/
public class No75_SortColor {

    /**
     * 思路:
     *      使用两个指针(lo,hi).控制在角标lo之前的数值都是0,在角标hi之后的数值都是2
     *      这里使用for循环的话需要注意,循环角标并不是从0~nums.length,而是0~hi
     * @param nums
     */
    public void sortColors(int[] nums) {
        if(nums==null||nums.length<2)
            return ;
        int lo=0,hi=nums.length-1;
        int i=0;
        while(i<=hi) {
            if(nums[i]==0){
                exch(nums,i,lo++);
            }else if(nums[i]==2){
                exch(nums,i,hi--);
                i--;
            }
            i++;
        }

    }

    void exch(int[] nums,int a, int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }

    public static void main(String[] args){
        No75_SortColor test=new No75_SortColor();
        int[] nums={2,2,2,2,2,2};
        CommonUtil.display(nums);
        test.sortColors(nums);
        CommonUtil.display(nums);
    }
}
