package com.luo.leetcode.slidingwindow;

/*

239. 滑动窗口最大值
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回滑动窗口中的最大值。
进阶：
你能在线性时间复杂度内解决此题吗？

示例:
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
*/

import com.luo.util.CommonUtil;

import java.util.LinkedList;

public class No239_maxSlidingWindow {

    /**
     * 思路一
     *      使用双端队列,队列保存着长度为k的窗口中的值索引.并且从大到小组织排序
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len=nums.length;
        if(len<2)
            return nums;
//        使用双端队列储存值索引,并且组织
        LinkedList<Integer> list=new LinkedList<>();
        int[] result=new int[len-k+1];
        for (int i = 0; i < len; i++) {
//            在窗口内尾部发现有小于等于当前元素的值,则弹出.保持顺序性
            while(!list.isEmpty() && nums[list.peekLast()]<=nums[i]){
                list.pollLast();
            }
            list.offerLast(i);
//            当发现窗口头部元素过期则清除
            if(list.peekFirst()<=i-k)
                list.pollFirst();
//            获取最大值,赋值给result
            if(i>=k-1)
                result[i-k+1]=nums[list.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args){
        No239_maxSlidingWindow test=new No239_maxSlidingWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] ints = test.maxSlidingWindow(nums, k);
        CommonUtil.display(ints);
    }
}
