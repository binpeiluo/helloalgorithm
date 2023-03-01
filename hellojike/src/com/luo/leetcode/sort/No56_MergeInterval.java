package com.luo.leetcode.sort;

/*
    56. 合并区间
    给出一个区间的集合，请合并所有重叠的区间。

        示例 1:

        输入: [[1,3],[2,6],[8,10],[15,18]]
        输出: [[1,6],[8,10],[15,18]]
        解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2:

        输入: [[1,4],[4,5]]
        输出: [[1,5]]
        解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
*/

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class No56_MergeInterval {
    /**
     * 思路是
     *      先将每个区间按大小排序
     *      然后判断合并区间
     *
     * 时间复杂度:nlogn  主要在排序
     * 空间复杂度:n
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
//        首先进行排序
        List<Interval> collect = Arrays.asList(intervals).stream().map(Interval::new).collect(Collectors.toList());
        collect.sort((i1,i2)->i1.start-i2.start==0?i1.end-i2.end:i1.start-i2.start);
        List<Interval> result=new ArrayList<>();
        for(int i=0;i<collect.size();i++){
            int start = collect.get(i).start;
            int end = collect.get(i).end;
//            这里是为了找到合并的区间
            while(i<collect.size()&&end>=collect.get(i).start){
                end=Math.max(end,collect.get(i).end);
                i++;
            }
            result.add(new Interval(new int[]{start,end}));
            i--;
        }

        int[][] temp=new int[result.size()][2];
        for(int i=0;i<result.size();i++){
            temp[i]=result.get(i).toArray();
        }
        return temp;
    }

    static class Interval{
        int start,end;
        public Interval(int[] interval){
            this.start=interval[0];
            this.end=interval[1];
        }
        int[] toArray(){
            return new int[]{start,end};
        }
    }

    public static void main(String[] args){
        No56_MergeInterval test=new No56_MergeInterval();
        int[][] param={{1,3},{8,10},{15,18},{2,6}};
        CommonUtil.display(param);
        int[][] merge = test.merge(param);
        CommonUtil.display(merge);

    }


}
