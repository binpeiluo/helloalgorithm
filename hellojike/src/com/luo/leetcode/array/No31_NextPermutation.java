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

    /**
     * 分为四个步骤
     *  1 从后往前找到下降的位置.有下降的位置说明有比该数字大的组合
     *  2 找到下降位置后,在后边找到大于此下降位置的最小数字.由此该数字被大于此的数字代替
     *  3 交换下降位置数值和大于此位置的数值.    既然知道了,那么替换
     *  4 从下降位置处倒置后边数值.             首位数字找到了,接下来此数字后后需要构成最小的数字,由于后边是递减的,所以倒置就是最小的数字了
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int len=nums.length;
//        从尾部向首部寻找开始下降的位置,
        int max=nums.length-1;
        while(max>0 && nums[max-1] >= nums[max]){
            max--;
        }
        if(max>0){
//        在下降位置后寻找大于该值的最小值
            int replace=max;
            while(replace+1<len && nums[replace+1] >nums[max-1] ){
                replace++;
            }
//            交换 max和 replace
            exch(nums,max-1,replace);
        }
//            倒置
        reverse(nums,max,len-1);
    }


    public static void main(String[] args){
        No31_NextPermutation test=new No31_NextPermutation();
        int[] nums={1,2,3};
        CommonUtil.display(nums);
        test.nextPermutation(nums);
        CommonUtil.display(nums);

        test.nextPermutation2(nums);
        CommonUtil.display(nums);

        test.nextPermutation2(nums);
        CommonUtil.display(nums);

        test.nextPermutation2(nums);
        CommonUtil.display(nums);

        test.nextPermutation2(nums);
        CommonUtil.display(nums);

        test.nextPermutation2(nums);
        CommonUtil.display(nums);

    }
}
