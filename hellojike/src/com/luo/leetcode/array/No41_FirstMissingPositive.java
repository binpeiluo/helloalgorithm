package com.luo.leetcode.array;

/*
    41. 缺失的第一个正数
    给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
        示例 1:
        输入: [1,2,0]
        输出: 3
        示例 2:
        输入: [3,4,-1,1]
        输出: 2
        示例 3:
        输入: [7,8,9,11,12]
        输出: 1
        说明:
        你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
*/
public class No41_FirstMissingPositive {

    /**
     * 思路:
     *      没有想出时间复杂度为O(n)的原地算法.
     *      用一个辅助数组统计数组中有哪些元素,然后在遍历这个辅助数组返回结果
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        boolean[] flag=new boolean[nums.length+1];
        for(int i=0,len=nums.length;i<len;i++){
            if(nums[i]<=len&&nums[i]>0)
                flag[nums[i]]=true;
        }
        int index=1;
        while(index<flag.length&&flag[index])
            index++;
        return index;
    }

    /**
     * 思路:
     *      判断该题,其缺失的数字肯定 大于0,小于等于数组的长度
     *      可以在遍历过程中,寻找该元素的正确位置(如果可能的话),然后在遍历寻找没有放置正确数字的位置
     *
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums){
        int index=0;
        while(index<nums.length){
            int num=nums[index];
//            位置index的正确数值应为index+1,另外为防止循环交换,还需要排除交换的两个元素为相同数值
//            找到其正确的位置,其正确的位置在于 num-1
            if(num>0&&num<=nums.length&&num!=index+1&&nums[index]!=nums[num-1])
                exch(nums,index,num-1);
            else
                index++;
        }
        index=0;
        while(index<nums.length&&nums[index]==index+1)
            index++;
        return index+1;
    }

    private void exch(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args){
        No41_FirstMissingPositive test=new No41_FirstMissingPositive();
        int[] nums={1,1};
        int i = test.firstMissingPositive(nums);
        System.out.println(i);

        int i1 = test.firstMissingPositive2(nums);
        System.out.println(i1);
    }
}
