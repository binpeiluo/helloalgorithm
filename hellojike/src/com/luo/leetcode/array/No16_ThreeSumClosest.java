package com.luo.leetcode.array;

import java.util.Arrays;

/*

    16. 最接近的三数之和
    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
    找出 nums 中的三个整数，使得它们的和与 target 最接近。
    返回这三个数的和。假定每组输入只存在唯一答案。
        例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
        与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
*/
public class No16_ThreeSumClosest {

    /**
     * 思路:
     *      该题跟求三数之和的题类似.不同之处在于求最接近的三数之和.
     *      别被糊弄了,在while循环中双指针不知lo与hi该如何移动
     *      当当前三数之和<target,则移动lo++,去发现是否有绝对值更小的三元素之和
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int result=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int lo=i+1,hi=nums.length-1;
            while(lo<hi){
                int tmp=nums[i]+nums[lo]+nums[hi];
                if(Math.abs(target-tmp)<Math.abs(target-result))
                    result=tmp;
                if(tmp>target)
                    hi--;
                else if(tmp<target)
                    lo++;
                else
                    return target;
            }
        }
        return result;
    }

    public static void main(String[] args){
        No16_ThreeSumClosest test=new No16_ThreeSumClosest();
        int[] nums={-1,2,1,-4};
        int target=1;
        int i = test.threeSumClosest(nums, target);
        System.out.println(i);
    }
}
