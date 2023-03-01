package com.luo.leetcode.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */
public class No45_jump {

    /**
     * 使用动态规划实现
     * 居然差点timeout
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
//        状态,当前的角标位置
//        明确dp数组含义,dp[i]表示走到角标为i的位置至少需要dp[i]步
//        状态转移  dp[i] ->    dp[i+ j] =Math.min(dp[i]+1,dp[i+j]), j= for 0 to nums[i]
//        base case, dp[0]=0;
        int len=nums.length;
        int[] dp=new int[len];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <=nums[i]; j++) {
                if(i+j<len&&dp[i]!=Integer.MAX_VALUE)
                    dp[i+j]=Math.min(dp[i]+1,dp[i+j]);
            }
        }
        return dp[len-1];
    }

    /**
     * 尝试使用bfs ,对比以下别人的代码简直了.
     * 这里是抽象的广度优先遍历，不使用queue结构，而是使用当前层能跳去的最大位置代表一层。跳到最尾部则进入下一层
     * @param nums
     * @return
     */
    public int jump2(int[] nums){
        int len=nums.length;
        int start=0,end=1,max=0;
        int res=0;
        while(end<len){

            for (int i = start; i < end; i++) {
                max=Math.max(max,i+nums[i]);
            }
            start=end;
            end=max+1;
            res++;
        }
        return res;
    }

    /**
     * 别人的思路:
     *      感觉是抽象的bfs
     *      每次从start遍历到end,找到能跳跃到的最远距离.这可以视为一层遍历
     * @param nums
     * @return
     */
    public int jump3(int[] nums){
        int len=nums.length;
//        代表每一层广度遍历的起点
        int start=0;
//        代表每一层广度遍历的终点后一个位置
        int end=1;
//        返回结果
        int res=0;
//        类似dfs思想，结束条件是当end==len
        while(end<len){
//            当前能跳跃到的最远距离
            int max=start;
//            这一层能达到的最远距离，为下一层广度遍历做准备
            for (int i = start; i < end; i++) {
                max=Math.max(max,i+nums[i]);
            }
//            当前层广度遍历完成，准备进入下一层遍历
            res++;
            start=end;
            end=max+1;
        }
        return res;
    }

    /**
     * 优化
     * @param nums
     * @return
     */
    public int jump4(int[] nums){
        int len=nums.length;
        int end=0;
        int res=0;
        int max=0;
        for (int i = 0; i < len; i++) {
            max=Math.max(i+nums[i],max);
            if(i==end){
                end=max;
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        No45_jump test=new No45_jump();
        int[] nums={2,3,1,1,4};
        int jump = test.jump(nums);
        System.out.println(jump);

        int i1 = test.jump2(nums);
        System.out.println(i1);

        int i = test.jump3(nums);
        System.out.println(i);
    }
}
