package com.luo.leetcode.doublepointer;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 *
 */
public class No27_removeElement {
    public int removeElement(int[] nums, int val) {
        int left,right;
        left=right=0;
        int len=nums.length;
        while(right<len){
            if(nums[right]!=val){
                nums[left]=nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    public static void main(String[] args) {
        No27_removeElement test=new No27_removeElement();

//        int[] ints={3,2,2,3};
//        int val = 3;

        int[] ints={0,1,2,2,3,0,4,2};
        int val = 2;

        int i = test.removeElement(ints, val);
        System.out.println(i);


    }
}
