package com.luo.leetcode.sort;

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
    57. 插入区间
    给出一个无重叠的 ，按照区间起始端点排序的区间列表。
        在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

        示例 1:
        输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
        输出: [[1,5],[6,9]]

        示例 2:
        输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        输出: [[1,2],[3,10],[12,16]]
        解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。*/
public class No57_InsertInterval {
    /**
     * 思路:偷懒做法
     * 56题已经解决把区间合并,可以利用这一方法构造一个新的二维数组
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length + 1][2];
        System.arraycopy(intervals, 0, result, 0, intervals.length);
        result[intervals.length] = newInterval;
        No56_MergeInterval test = new No56_MergeInterval();
        int[][] merge = test.merge(result);
        return merge;
    }

    /**
     * 思路:
     *      将已经排序的区间数组分为三部分.
     *      第一部分,区间的尾部小于先插入区间的头部.直接输出即可
     *      第二部分,合并区间.
     *          合并区间=[min(新区间头部,当前区间头部),max(新插入区间尾部,当前区间尾部)]
     *      第三部分,区间的头部大于中间部分合并部分的尾部
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Interval> collect = Arrays.asList(intervals).stream().map(Interval::new).collect(Collectors.toList());
        List<Interval> resultInterval = new ArrayList<>();
        int index = 0;
//        处理前边部分
        while (index < collect.size()
                && collect.get(index).end < newInterval[0])
            resultInterval.add(collect.get(index++));
//        处理合并部分
        while(index<collect.size()&&collect.get(index).start<=newInterval[1]){
            newInterval[0]=Math.min(collect.get(index).start,newInterval[0]);
            newInterval[1]=Math.max(collect.get(index).end,newInterval[1]);
            index++;
        }
        resultInterval.add(new Interval(newInterval));

//        处理后边部分
        while (index < collect.size()) {
            resultInterval.add(collect.get(index));
            index++;
        }

        int[][] temp=new int[resultInterval.size()][2];
        for(int i=0;i<resultInterval.size();i++){
            temp[i]=resultInterval.get(i).toArray();
        }
        return temp;
    }


    static class Interval {
        int start, end;

        public Interval(int[] interval) {
            this.start = interval[0];
            this.end = interval[1];
        }

        int[] toArray() {
            return new int[]{start, end};
        }
    }

    public static void main(String[] args) {
        No57_InsertInterval test = new No57_InsertInterval();
//        int[][] intervals = {{1, 3}, {6, 9}};
//        int[] newInterval = {2, 5};


        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4, 8};

//        int[][] insert = test.insert1(intervals, newInterval);
        int[][] insert = test.insert(intervals, newInterval);
        CommonUtil.display(insert);
    }

}
