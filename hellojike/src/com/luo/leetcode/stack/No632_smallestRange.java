package com.luo.leetcode.stack;

import com.luo.util.CommonUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. 最小区间
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例 1:
 *
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 注意:
 *
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -105 <= 元素的值 <= 105
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 *
 */
public class No632_smallestRange {
    /**
     * k个数组,寻找最小区间,使得每个数组至少有一个包含在内.
     * 可以转换为 每个数组选择一个数,使得组成的区间最小
     * k个数组有序,可以使用最小堆结构储存每个数组选择的元素.
     * 然后弹出最小的元素,压入弹出元素所属数组的下一个元素
     *
     * 时间复杂度:   O(nklogk)   n为列表平均长度
     * 空间复杂度:   O(k)        优先队列空间
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int leftIndex=Integer.MAX_VALUE,rightValue=Integer.MIN_VALUE;
        int minLeftValue=0,minRightValue=0;
        int diff=Integer.MAX_VALUE;
        PriorityQueue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt(a -> nums.get(a[0]).get(a[1])));
        for (int i = 0,len=nums.size(); i < len; i++) {
            rightValue=Math.max(rightValue,nums.get(i).get(0));
            queue.offer(new int[]{i,0});
        }
        while(true){
            int[] currMinPair = queue.poll();
            leftIndex=currMinPair[1];
            if(diff==Integer.MAX_VALUE||rightValue-nums.get(currMinPair[0]).get(leftIndex)<diff){
                minLeftValue=nums.get(currMinPair[0]).get(leftIndex);
                minRightValue=rightValue;
                diff=minRightValue-minLeftValue;
            }
            if(currMinPair[1]==nums.get(currMinPair[0]).size()-1){
                break;
            }else{
                rightValue=Math.max(rightValue,nums.get(currMinPair[0]).get(currMinPair[1]+1));
                queue.offer(new int[]{currMinPair[0],currMinPair[1]+1});
            }
        }
        return new int[]{minLeftValue,minRightValue};
    }

    public static void main(String[] args){
        No632_smallestRange test=new No632_smallestRange();
//        List<List<Integer>> nums= Arrays.asList(
//                Arrays.asList(4,10,15,24,26),
//                Arrays.asList(0,9,12,20),
//                Arrays.asList(5,18,22,30)
//        );

        List<List<Integer>> nums= Arrays.asList(
                Arrays.asList(4)
        );
        int[] ints = test.smallestRange(nums);
        CommonUtil.display(ints);
    }
}
