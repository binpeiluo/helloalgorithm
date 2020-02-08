package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/*
    31. 下一个排列
    实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
        如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
        必须原地修改，只允许使用额外常数空间。
        以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1
*/
public class No31_NextPermutation {

    /**
     * 思路:
     *      全排列的思想,假如数组元素从前到后的顺序是递减的,那么该值已经是最大的了
     *      从后往前找出 nums[i]<nums[i+1]的元素
     *      接着从后往前找出大于nums[i]的最小元素,交换nums[i]和nums[j]
     *      从i+1元素开始算,到最后边都是递减的,需要倒序成最小的组合
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums.length<2)
            return;
//        1 从后往前寻找数值递增的两个元素,在此元素之后的数字都是递减的
        int len=nums.length;
        int i=len-2;
        while(i>=0&&nums[i]>=nums[i+1])
            i--;

        if(i>=0){
//        2 从后往前寻找大于nums[i]的最小数字
            int j=len-1;
            while(j>=0&&nums[j]<=nums[i])
                j--;
            exch(nums,i,j);
        }
        reverse(nums,i+1,len-1);
    }

    private void exch(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    private void reverse(int[] nums,int from ,int to){
        for(int i=0,len=(to-from+1)/2;i<len;i++){
            exch(nums,from+i,to-i);
        }
    }


    public static void main(String[] args){
        No31_NextPermutation test=new No31_NextPermutation();
        int[] nums={1,3,2};
        CommonUtil.display(nums);
        test.nextPermutation(nums);
        CommonUtil.display(nums);
    }
}