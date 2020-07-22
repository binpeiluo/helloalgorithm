package com.luo.leetcode.trace;


/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */
public class No312_maxCoins {

    /**
     * 没有好想法,直接回溯一把梭哈
     * 回溯时间复杂度爆表 O(n!)
     *
     * 遇到时间复杂度为n!的问题时 可以使用分治思维解决.
     * 比如戳破一个气球时,将数组分为两部分.nums[0,n]在位置i戳破气球,nums[0,i-1]和nums[i+1,n]两部分
     * 但是! 两个逻辑上相连的元素却被隔开,如此计算将会变得复杂
     *
     * 可以另外换种分治方式. 我们不戳破 k 元素,避免给分出来的两个数组造成计算复杂
     * 对于 nums[i,j]先求出nums[i+1,k-1]和nums[k+1,j-1]这两部分的最大值.如此在戳破元素k,与元素k相邻的元素分别是nums[i]和nums[j]
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        trace(0,nums,0);
        return max;
    }
    int max=Integer.MIN_VALUE;

    private void trace(int result,int[] nums,int count){
//        结束递归条件
        int len=nums.length;
        if(count==len){
            max=Math.max(max,result);
            return;
        }
//        选择列表
        for (int i = 0; i < len; i++) {
            if(nums[i]==-1){
                continue;
            }
//            做出选择
            int currNum=nums[i];
            nums[i]=-1;
//            找到该位置的前一个没有戳破的元素
            int leftIndex=i-1;
            while(leftIndex>=0&&nums[leftIndex]==-1){
                leftIndex--;
            }
            int rightIndex=i+1;
            while(rightIndex<len&&nums[rightIndex]==-1){
                rightIndex++;
            }
            int currMutil=(leftIndex==-1?1:nums[leftIndex])*currNum*(rightIndex==len?1:nums[rightIndex]);
            trace(result+currMutil,nums,count+1);
//            撤销选择
            nums[i]=currNum;
        }
    }

    public int maxCoins2(int[] nums) {
        return divide2(nums,0,nums.length-1);
    }

    private int divide2(int[] nums,int start,int end){
        if(start==end-1){
            return 0;
        }
//        if(start==end){
//            return nums[start];
//        }
        int result=0;
        for (int i = start+1; i <end; i++) {
            int left=divide2(nums,start,i);
            int right=divide2(nums,i,end);
            int curr=nums[start]*nums[i]*nums[end];
            result=Math.max(result,left+right+curr);
        }
        return result;
    }

    public static void main(String[] args){
        No312_maxCoins test=new No312_maxCoins();
//        int[] nums={3,1,5,8};
        int[] nums={3,4,5,6,7,5,7,8,5,3,2,5};

        int i = test.maxCoins(nums);
        System.out.println(i);

        int i1 = test.maxCoins2(nums);
        System.out.println(i1);
    }
}
