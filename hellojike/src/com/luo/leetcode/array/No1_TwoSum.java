package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

import java.util.HashMap;

/*
    1. 两数之和
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
        示例:
        给定 nums = [2, 7, 11, 15], target = 9
        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
*/
public class No1_TwoSum {

    /**
     * 思路:
     *      暴力法.
     *      两次for循环
     *
     *      时间复杂度:  O(n^2)
     *      空间复杂度:  O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums.length<2)
            return new int[0];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 思路:
     *      使用hashmap储存k(元素)->v(角标)的映射关系
     *
     *      时间复杂度:  O(n)
     *      空间复杂度:  O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums,int target){
        if(nums.length<2)
            return new int[0];
//        map 储存k元素所在数组的角标v
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }

    public static void main(String[] args){
        No1_TwoSum test=new No1_TwoSum();
        int[] nums={2, 7, 11, 15};
        int target=9;
        int[] ints = test.twoSum2(nums, target);
        CommonUtil.display(ints);
    }
}
