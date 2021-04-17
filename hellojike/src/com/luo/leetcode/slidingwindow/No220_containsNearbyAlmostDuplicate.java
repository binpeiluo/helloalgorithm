package com.luo.leetcode.slidingwindow;

import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。
 * 请你判断是否存在两个下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 */
public class No220_containsNearbyAlmostDuplicate {

    /**
     * 滑动窗口
     * 考虑需要使用数据结构来储存窗口内的数据
     * 需要方便地删除数据和添加数据
     * 需要方便地查找出是否有大于某一个数的元素
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len=nums.length;
        TreeSet<Long> set=new TreeSet<>();
        for (int i = 0; i < len; i++) {
            long min=(long)nums[i]-(long)t;
            Long ceiling = set.ceiling(min);
            if(ceiling!=null&&ceiling<=(long)t+(long)nums[i]){
                return true;
            }
            set.add((long)nums[i]);
            if(i>=k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        No220_containsNearbyAlmostDuplicate test=new No220_containsNearbyAlmostDuplicate();

//        int[] nums = {1,2,3,1};
//        int k = 3, t = 0;

//        int[] nums = {1,0,1,1};
//        int k = 1, t = 2;

//        int[] nums = {1,5,9,1,5,9};
//        int k = 2, t = 3;

        int[] nums={2147483640,2147483641};
        int k=1,t=100;

        boolean b = test.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(b);

    }
}
