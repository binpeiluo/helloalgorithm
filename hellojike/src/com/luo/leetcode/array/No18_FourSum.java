package com.luo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    18. 四数之和
    给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
        注意：
        答案中不可以包含重复的四元组。
        示例：
        给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
        满足要求的四元组集合为：
        [
            [-1,  0, 0, 1],
            [-2, -1, 1, 2],
            [-2,  0, 0, 2]
        ]
*/

public class No18_FourSum {

    /**
     * 思路:
     *      使用四个指针,(i,j,p,q).两层for循环加上一次while条件
     *
     *      值得注意的是如何避免产生相同的四元数组
     *      此方法在执行耗时以及消耗内存为 62 ms	43.5 MB
     *      应该可以再优化
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length<4)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i+1; j < nums.length-2; j++) {
                int p=j+1,q=nums.length-1;
                while(p<q){
                    int sum=nums[i]+nums[j]+nums[p]+nums[q];
                    if(sum==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[p],nums[q]));
                        while(p<q&&nums[p+1]==nums[p])
                            p++;
                        while(p<q&&nums[q-1]==nums[q])
                            q--;
                        p++;
                        q--;
                    }else if(sum<target)
                        p++;
                    else
                        q--;
                }
                while(j<nums.length-2-1&&nums[j+1]==nums[j])
                    j++;
            }
            while(i<nums.length-3-1&&nums[i+1]==nums[i])
                i++;
        }
        return result;
    }

    public static void main(String[] args){
        No18_FourSum test=new No18_FourSum();
        int[] nums={-1,-5,-5,-3,2,5,0,4};
        int target=-7;
        List<List<Integer>> lists = test.fourSum(nums, target);
        System.out.println(lists);
    }
}
