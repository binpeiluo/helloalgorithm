package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠
 * （如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 *
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 */
public class No57_insert {

    /**
     * 思路:
     *      其实跟no.56合并区间很类似,构建一个新的数组,包含原来的区间以及新插入的区间.
     *      然后合并区间即可
     *
     * 空间复杂度:   O(2*n) ,需要构建一个新的数组和list辅助 列表
     * 时间复杂度:   O(n),   插入新区间为n,合并新区间也为n
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int oldLen=intervals.length;
        int[][] allintervals=new int[oldLen+1][2];
        if(oldLen==0){
            allintervals[0]=newInterval;
            return allintervals;
        }
//        原来的区间已经有序,那么构建新数组只需要插入即可.
//        注意边界条件.这里使用两个指针指向原区间数组的角标与新数组区间的角标.
//        当这两个指针不相等时,说明待插入区间已经被插入.可以不考虑待插入区间了.
//        有可能待插入区间是最大的,故还需要考虑指向原数组的指针已经达到最大的情况,可以直接插入新区间
        for (int i = 0,j=0; i < oldLen+1; i++) {
            if( j<oldLen && (i!=j || intervals[j][0]<newInterval[0])){
                allintervals[i]=intervals[j];
                j++;
            }else
                allintervals[i]=newInterval;
        }
        List<int[]> resultList=new ArrayList<>();
        int index=0;
//        从小到大迭代区间
        while(index<oldLen+1){
//            当发现区间相连时,使用两个辅助变量指向这些相连区间的最小值和最大值
//            找到后插入即可
            int leftStart=allintervals[index][0];
            int leftEnd=allintervals[index][1];
            while(index+1<oldLen+1 && allintervals[index+1][0] <= leftEnd){
                leftEnd=Math.max(leftEnd,allintervals[index+1][1]);
                index++;
            }
            resultList.add(new int[]{leftStart,leftEnd});
            index++;
        }
        int[][] result=new int[resultList.size()][2];
        return resultList.toArray(result);
    }
    
    public static void main(String[] args){
        No57_insert test=new No57_insert();
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval = {2,5};

        int[][] intervals = {{1,5}};
        int[] newInterval = {2,3};

        int[][] insert = test.insert(intervals, newInterval);
        CommonUtil.display(insert);
    }
}
