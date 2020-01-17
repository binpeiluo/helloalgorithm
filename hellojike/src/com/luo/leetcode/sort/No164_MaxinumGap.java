package com.luo.leetcode.sort;


import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/*
    164. 最大间距
    给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
        如果数组元素个数小于 2，则返回 0。
        示例 1:
        输入: [3,6,9,1]
        输出: 3
        解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
        示例 2:
        输入: [10]
        输出: 0
        解释: 数组元素个数小于 2，因此返回 0。
        说明:

        你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
        请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
*/
public class No164_MaxinumGap {

    /**
     * 线性时间复杂度和线性空间复杂度,只有线性排序能做到
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucket.add(new ArrayList<>());
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int exp = 1;
        while (max > 0) {
            //            清空
            bucket.stream().forEach(list -> list.clear());
            for (int i = 0; i < nums.length; i++) {
                bucket.get(nums[i] / exp % 10).add(nums[i]);
            }
            //            填充回原来的数组
            int index = 0;
            for (int i = 0; i < bucket.size(); i++) {
                for (int j = 0; j < bucket.get(i).size(); j++) {
                    nums[index++] = bucket.get(i).get(j);
                }
            }
            exp *= 10;
            max /=10;
        }
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, nums[i] - nums[i - 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        No164_MaxinumGap test = new No164_MaxinumGap();
        int[] nums = {3, 6, 9, 1,100,50};
        CommonUtil.display(nums);
        int i = test.maximumGap(nums);
        System.out.println("result==" + i);
    }
}
