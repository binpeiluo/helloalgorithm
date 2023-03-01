package com.luo.leetcode.binarysearch;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，
 * 我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同
 *
 */
@SuppressWarnings("Duplicates")
public class No_offer_11_minArray {

    /**
     * 二分查找
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int len=numbers.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(numbers[mid]<numbers[right]){
//                如果中间元素小于右边界,
                right=mid;
            }else if(numbers[mid]>numbers[right]){
//                假如中间元素大于右边界,
                left=mid+1;
            }else if(numbers[mid]==numbers[right]){
//                假如中间元素等于右边界,判断不出
                right--;
            }
        }
        return numbers[left];
    }

    public static void main(String[] args){
        No_offer_11_minArray test=new No_offer_11_minArray();
//        int[] nums={3,4,5,1,2};
//        int[] nums={2,2,2,0,1};
        int[] nums={2,2,2,3,4};

        int i = test.minArray(nums);
        System.out.println(i);
    }
}
