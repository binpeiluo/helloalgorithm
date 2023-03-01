package com.luo.leetcode.dp;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。
 * 但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class No55_canJump {

    /**
     * 思路:
     *      navie方法
     *      应该页可以使用动态规划
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
//        遍历一次数组,判断是否能达到各个元素
        int len=nums.length;
//        表示当前能到达的距离
        int index=0;
        for (int i = 0; i < len; i++) {
            if(i<=index)
                index=Math.max(index,i+nums[i]);
        }
        return index>=len-1;
    }



    public static void main(String[] args){
        No55_canJump test=new No55_canJump();
//        int[] nums={2,3,1,1,4};
        int[] nums={3,2,1,0,4};
        boolean b = test.canJump(nums);
        System.out.println(b);
    }
}
