package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class No56_merge {

    /**
     * 思路:
     *      将区间按照其实位置升序排序,当其实位置一样则按照结束位置升序排序
     *      然后遍历区间列表,
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(i1,i2)->(i1[0]-i2[0]!=0)?(i1[0]-i2[0]):(i1[1]-i2[1]));
        CommonUtil.display(intervals);
        List<int[]> result=new ArrayList<>();
        int len=intervals.length;
        int index=0;
        while(index<len){
            int start=intervals[index][0];
            int end=intervals[index][1];
            while(index+1<len && intervals[index+1][0]<=end){
                index++;
                end=Math.max(end,intervals[index][1]);
            }
            result.add(new int[]{start,end});
            index++;
        }
        int[][] res=new int[result.size()][2];
        result.toArray(res);
        return res;
    }

    public static void main(String[] args){
        No56_merge test=new No56_merge();
//        int[][] intervals={{8,10},{2,3},{1,3},{15,18},{2,6}};
//        int[][] intervals={{2,4},{2,3}};
        int[][] intervals={{2,3},{4,5},{6,7},{8,9},{1,10}};
//        int[][] intervals={{1,3},{0,2},{2,3},{4,6},{4,5},{5,5},{0,2},{3,3}};
        int[][] merge = test.merge(intervals);
        CommonUtil.display(merge);

    }
}
