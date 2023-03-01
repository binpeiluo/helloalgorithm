package com.luo.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 446. 等差数列划分 II - 子序列
 *
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 *
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 *
 * 示例 1：
 * 输入：nums = [2,4,6,8,10]
 * 输出：7
 * 解释：所有的等差子序列为：
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 *
 * 示例 2：
 * 输入：nums = [7,7,7,7,7]
 * 输出：16
 * 解释：数组中的任意子序列都是等差子序列。
 *
 */
public class No446_numberOfArithmeticSlices {

    /**
     * 跟 413 的区别在于这里是子序列,另一题是子数组
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if(len<2){
            return 0;
        }
        List<Integer> list=new ArrayList<>();
//        从角标0开始
        trace(list,nums,0);
        return result;
    }

    int result=0;
    private void trace(List<Integer> list,int[] nums,int index){
        if(index==nums.length){
            return;
        }
//        思考这里 选择列表，从index位置开始都可以被选择 所以上层不需要遍历
        for (int i = index; i < nums.length; i++) {
//            选择的列表只有一个元素，则直接放入
            if(list.size()<=1){
                list.add(nums[i]);
                trace(list,nums,i+1);
                list.remove(list.size()-1);
            }else{
//                列表元素大于一个元素，则判断是否组成等差数组
                long d=(long)list.get(1)-(long)list.get(0);
                long currD=(long)nums[i]-(long)list.get(list.size()-1);
                if(currD==d){
                    result++;
                    list.add(nums[i]);
                    trace(list,nums,i+1);
                    list.remove(list.size()-1);
                }
            }
        }
    }

    /**
     * 回溯算法超时  尝试使用动态规划
     * 太难了
     * dp[i] 表示已 nums[i] 为结尾的组成等差数组的最大长度
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices2(int[] nums) {
        int len = nums.length;
        if(len<2){
            return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        No446_numberOfArithmeticSlices test=new No446_numberOfArithmeticSlices();
//        int[] nums = {2,4,6,8,10};
//        int[] nums = {7,7,7,7,7};
        int[] nums = {0,2000000000,-294967296};
        int result = test.numberOfArithmeticSlices(nums);
        System.out.printf("result==%d", result);
    }
}
