package com.luo.sevendays.day1.array;

import java.util.Random;

/**
 * 查找缺失的第一个正数
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        for(int i=0,len=nums.length;i<len;i++){
            if(nums[i]<1||nums[i]>len||nums[i]==i+1){
                continue;
            }
            exch(nums,nums[i],nums[i]-1);
//            i--;
        }
        this.display(nums);
        int i=0;
        while(nums[i]==i+1)
            i++;
        return i+1;
    }
    public void exch(int[] nums,int i,int j){
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
    public void display(int[] a){
        System.out.print("数组=");
        for(int n:a){
            System.out.print("\t"+n);
        }
        System.out.println();
    }

    public static void main(String[] args){
        FirstMissingPositive temp=new FirstMissingPositive();
        Random r=new Random();
//        int[] input=new int[10];
//        for(int i=0,len=input.length;i<len;i++){
//            input[i]=r.nextInt(20);
//        }

        int[] input=new int[]{4,9,9,4,3,5,5,11,15,11};
        temp.display(input);
        int i = temp.firstMissingPositive(input);
        System.out.println(i);
    }
}
