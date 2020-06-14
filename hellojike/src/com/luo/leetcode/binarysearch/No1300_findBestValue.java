package com.luo.leetcode.binarysearch;

import java.util.Arrays;

/*
1300. 转变数组后最接近目标值的数组和
给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target 
（最接近表示两者之差的绝对值最小）。
如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
请注意，答案不一定是 arr 中的数字。
示例 1：
输入：arr = [4,9,3], target = 10
输出：3
解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
示例 2：
输入：arr = [2,3,5], target = 10
输出：5
示例 3：
输入：arr = [60864,25176,27249,21296,20204], target = 56803
输出：11361
提示：

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5
*/
public class No1300_findBestValue {

    /**
     * 思考返回的result的上下限是什么.当target为0时,result下限就为0.而上线明显就是数组中的最大值
     * 时间复杂度:   O(nlogn)    排序复杂度nlogn  +   循环n次,每次查找,也是nlogn
     * 空间复杂度:   O(n)        排序使用的栈空间logn    +   额外数组
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int len=arr.length;
        int[] prefix=new int[len+1];
        for (int i = 0; i < len; i++) {
            prefix[i+1]=prefix[i]+arr[i];
        }
        int r=arr[len-1];
        int result=0,resultSum=0;
        for (int i = 0; i <=r; i++) {
//          找不到元素时,会返回插入位置  (-(insertion point) - 1)
            int index = Arrays.binarySearch(arr, i);
            if(index<0){
                index=-(index+1);
            }
            int sum=prefix[index]+(len-index)*i;
            if(Math.abs(sum-target)<Math.abs(resultSum-target)){
                result=i;
                resultSum=sum;
            }
        }
        return result;
    }



    public static void main(String[] args){
        No1300_findBestValue test=new No1300_findBestValue();
        int[] arr = {4,9,3};
        int target = 10;

//        int[] arr={60864,25176,27249,21296,20204};
//        int target = 56803;

        int bestValue = test.findBestValue(arr, target);
        System.out.println(bestValue);
    }

}
