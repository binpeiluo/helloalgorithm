package com.luo.labuladong.highfrequence;

import java.util.*;

/**
 * 高频题目
 *      学生座位调度,挺有意思
 */
public class TestStudentSchedule2 {
    // 将端点 p 映射到以 p 为左端点的线段
    private Map<Integer, int[]> startMap;
    // 将端点 p 映射到以 p 为右端点的线段
    private Map<Integer, int[]> endMap;
    // 根据线段⻓度从⼩到⼤存放所有线段
    private TreeSet<int[]> pq;
    private int N;

    public TestStudentSchedule2(int N){
        this.N = N;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>((a, b) -> {
            int distA = distance(a);
            int distB = distance(b);
            // 如果⻓度相同，就⽐较索引
            if (distA == distB)
                return b[0] - a[0];
            return distA - distB;
        });
        // 在有序集合中先放⼀个虚拟线段
        addInterval(new int[] {-1, N});
    }
    /* 去除⼀个线段 */
    private void removeInterval(int[] intv) {
        pq.remove(intv);
        startMap.remove(intv[0]);
        endMap.remove(intv[1]);
    }
    /* 增加⼀个线段 */
    private void addInterval(int[] intv) {
        pq.add(intv);
        startMap.put(intv[0], intv);
        endMap.put(intv[1], intv);
    }
    /* 计算⼀个线段的⻓度 */
    private int distance(int[] intv) {
        int x = intv[0];
        int y = intv[1];
        if (x == -1) return y;
        if (y == N) return N - 1 - x;
        // 中点和端点之间的⻓度
        return (y - x) / 2;
    }
    public int seat() {
// 从有序集合拿出最⻓的线段
        int[] longest = pq.last();
        int x = longest[0];
        int y = longest[1];
        int seat;
        if (x == -1) { // 情况⼀
            seat = 0;
        } else if (y == N) { // 情况⼆
            seat = N - 1;
        } else { // 情况三
            seat = (y - x) / 2 + x;
        }
// 将最⻓的线段分成两段
        int[] left = new int[] {x, seat};
        int[] right = new int[] {seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }
    public void leave(int p) {
// 将 p 左右的线段找出来

        int[] right = startMap.get(p);
        int[] left = endMap.get(p);
// 合并两个线段成为⼀个线段
        int[] merged = new int[] {left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    public static void main(String[] args){
        TestStudentSchedule2 test=new TestStudentSchedule2(10);
        test.seat();
        test.seat();
        test.seat();
        test.seat();
        test.leave(4);
        test.seat();

        System.out.println(test.pq);
        test.pq.stream().forEach(interval->{
            System.out.println(interval[0]+" - "+interval[1]);
        });
    }
}
