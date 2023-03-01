package com.luo.labuladong.highfrequence;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * 高频题目
 *      学生座位调度,挺有意思
 *
 *      在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 *
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * 返回 ExamRoom(int N) 类，
 *  它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；
 *  函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。
 *  每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 *
 * 示例：
 * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * 输出：[null,0,9,4,2,null,5]
 * 解释：
 * ExamRoom(10) -> null
 * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
 * seat() -> 9，学生最后坐在 9 号座位上。
 * seat() -> 4，学生最后坐在 4 号座位上。
 * seat() -> 2，学生最后坐在 2 号座位上。
 * leave(4) -> null
 * seat() -> 5，学生最后坐在 5 号座位上。
 *
 */
public class TestStudentSchedule {

    private TreeSet<int[]> pq;
    private HashMap<Integer,int[]> startMap;
    private HashMap<Integer,int[]> endMap;
    private int N;

    /**
     * 这是一道很有意思的题目.考验对数据结构的理解
     * 学生坐座位的问题可以抽象成线段的问题.
     * 学生选择座位,学生需要选择一段距离最长的线段,然后二分.将其线段分为两段.
     * 而学生离开座位,可以视为两条线段合并成一条线段.
     *
     * 这种动态处理寻找最值的要求,需要使用有序的数据结构.符合要求的有两种,二叉堆和二叉平衡查找树.
     * 二叉堆对于查找最值的时间复杂度为O(nlogn),但是仅限于最值查找.对于学生离开座位需要删除线段则不支持
     * 而二叉平衡查找树,比如红黑树对于增删查时间复杂度都是O(nlogn)
     * @param N
     */
    public TestStudentSchedule(int N) {
        this.N=N;
        pq=new TreeSet<>((a,b)->{
            int distA = interval(a);
            int distB = interval(b);
            // 如果⻓度相同，就⽐较索引
            if (distA == distB)
                return b[0] - a[0];
            return distA - distB;
        });
        startMap=new HashMap<>();
        endMap=new HashMap<>();
        pq.add(new int[]{-1,N});
    }

    public int seat() {
        int seat;
        int[] last = pq.last();
        int x=last[0];
        int y=last[1];
//        判断是否有人
        if(x==-1)
            seat=0;
        else if(y==N)
            seat=N-1;
        else{
            seat=(y-x)/2+x;
        }
        int[] left = new int[]{x,seat};
        int[] right = new int[]{seat, y};
        removeInterval(last);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        int[] end = startMap.get(p);
        int[] start = endMap.get(p);
//        int[] merged = new int[] {start[0], end[1]};
        removeInterval(start);
        removeInterval(end);
//        addInterval(merged);
        addInterval(new int[]{start[0],end[1]});


//        int[] right = startMap.get(p);
//        int[] left = endMap.get(p);
//// 合并两个线段成为⼀个线段
//        int[] merged = new int[] {left[0], right[1]};
//        removeInterval(left);
//        removeInterval(right);
//        addInterval(merged);
    }

    private void removeInterval(int[] interval){
//        pq.remove(interval);
//        startMap.remove(interval[0]);
//        endMap.remove(interval[1]);

        pq.remove(interval);
        startMap.remove(interval[0]);
        endMap.remove(interval[1]);
    }
    private void addInterval(int[] interval){
//        pq.add(interval);
//        startMap.put(interval[0],interval);
//        endMap.put(interval[1],interval);
        pq.add(interval);
        startMap.put(interval[0], interval);
        endMap.put(interval[1], interval);
    }
    private int interval(int[] interval){
        int x = interval[0];
        int y = interval[1];
        if (x == -1) return y;
        if (y == N) return N - 1 - x;
        // 中点和端点之间的⻓度
        return (y - x) / 2;
    }

    public static void main(String[] args){
        TestStudentSchedule test=new TestStudentSchedule(10);
        test.seat();
        test.seat();
        test.seat();
        test.seat();
        test.leave(4);
        test.seat();

        test.pq.stream().forEach(interval->{
            System.out.println(interval[0]+" - " +interval[1]);
        });
    }

}
