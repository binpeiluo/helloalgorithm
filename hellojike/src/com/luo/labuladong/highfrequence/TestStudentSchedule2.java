package com.luo.labuladong.highfrequence;

import java.util.*;

/**
 * 高频题目
 *      学生座位调度,挺有意思
 */
public class TestStudentSchedule2 {

    private int n;
    private TreeSet<int[]> treeSet;
    private Map<Integer,int[]> startMap;
    private Map<Integer,int[]> endMap;

    public TestStudentSchedule2(int N){
        this.n=N;
        treeSet=new TreeSet<>((a,b)->{
            int distanceA=distance(a);
            int distanceB=distance(b);
            if(distanceA==distanceB)
                return b[0]-a[0];
            return distanceA-distanceB;
        });
        startMap=new HashMap<>();
        endMap=new HashMap<>();
        treeSet.add(new int[]{-1,N});
    }

    /**
     * 学生找到座位坐下,可以视为将最长一条线段二分切分为两条.
     * @return
     */
    public int seat() {
        int seat;
        int[] last = treeSet.last();
        if(last[0]==-1)
            seat=0;
        else if(last[1]==n)
            seat=n-1;
        else{
            seat=last[0]+(last[1]-last[0])/2;
        }
        removeInterval(last);
        addInterval(new int[]{last[0],seat});
        addInterval(new int[]{seat,last[1]});
        return seat;
    }

    /**
     * 学生离开座位可以抽象为将两条线段合并为一条
     * @param p
     */
    public void leave(int p) {
        int[] right=startMap.get(p);
        int[] left = endMap.get(p);
        removeInterval(right);
        removeInterval(left);
        addInterval(new int[]{left[0],right[1]});
    }

    private void removeInterval(int[] interval){
        treeSet.remove(interval);
        startMap.remove(interval[0]);
        endMap.remove(interval[1]);
    }

    private void addInterval(int[] interval){
        treeSet.add(interval);
        startMap.put(interval[0],interval);
        endMap.put(interval[1],interval);
    }

    /**
     * 计算线段的距离,在这里距离3和4是一样的
     * @param interval
     * @return
     */
    private int distance(int[] interval){
        return (interval[1]-interval[0]-1+1)/2;
    }

    public static void main(String[] args){
        TestStudentSchedule2 test=new TestStudentSchedule2(10);
        test.seat();
        printTreeSet(test.treeSet);
        test.seat();
        printTreeSet(test.treeSet);
        test.seat();
        printTreeSet(test.treeSet);
        test.seat();
        printTreeSet(test.treeSet);
        test.leave(4);
        printTreeSet(test.treeSet);
        test.seat();
        printTreeSet(test.treeSet);

    }

    private static void printTreeSet(TreeSet<int[]> treeSet){
        System.out.println("printTreeSet");
        treeSet.stream().forEach(interval->{
            System.out.println(interval[0]+" - "+interval[1]);
        });
    }
}
