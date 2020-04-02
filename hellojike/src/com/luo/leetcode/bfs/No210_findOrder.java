package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 210. 课程表 II
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 */
public class No210_findOrder {

    /**
     * 维护节点入度列表,当发现节点入度为0时可以学习当前课程
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result=new int[numCourses];
        int index=0;
        int[] indegress=new int[numCourses];
        List<List<Integer>> adjust=new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjust.add(new ArrayList<>());
        }
        for (int[] p:prerequisites){
            indegress[p[0]]++;
            adjust.get(p[1]).add(p[0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i = 0,len=numCourses; i < len; i++) {
            if(indegress[i]==0)
                queue.offer(i);
        }
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            numCourses--;
            result[index++]=poll;
            for (Integer curr:adjust.get(poll)) {
                indegress[curr]--;
                if(indegress[curr]==0)
                    queue.offer(curr);
            }
        }
        if(numCourses!=0)
            return new int[0];
        else
            return result;

    }


    public static void main(String[] args){
        No210_findOrder test=new No210_findOrder();
        int numCourses=2;
        int[][] prerequisites={};

//        int numCourses=4;
//        int[][] prerequisites={{1,0},{2,0},{3,1},{3,2}};
        int[] order = test.findOrder(numCourses, prerequisites);
        CommonUtil.display(order);
    }
}
