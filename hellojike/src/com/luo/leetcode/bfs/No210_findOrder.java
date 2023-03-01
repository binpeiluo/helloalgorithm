package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.*;

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
@SuppressWarnings("Duplicates")
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

    /**
     * 求拓扑排序
     * 这道课程排序问题可以抽象为拓扑排序问题,
     * 将课程视为图的一个点,将课程前提视为边.
     *
     * 可以使用深度优先搜寻,任意搜索一个节点.
     * 节点的访问状态可以分为三种,1 未被搜索过 2 正在搜索中 3 已经搜索完
     * 当遍历节点时,发现该节点的所有入边对应的节点已经被搜索过,则可以输出该节点
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges=new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
//        构建图 a->b 表示 a学习完后才可以学习b
        for (int[] edge:prerequisites){
            edges.get(edge[1]).add(edge[0]);
        }
        int[] visited=new int[numCourses];
        int[] result=new int[numCourses];
        Stack<Integer> stack=new Stack();
        for(int i=0;i<numCourses && valid2;i++){
            if(visited[i]==0){
                dfs(edges,visited,stack,i);
            }
        }
        if(!valid2){
            return new int[]{};
        }else{
//          找到的第一个节点为最后一个需要学习的, 使用栈储存的话
//          为什么呢?为什么第一个节点时最后一个学习的呢? 因为使用的是dfs,遍历到最深处然后入栈.
            for (int i = 0; i < numCourses; i++) {
                 result[i]=stack.pop();
            }
            return result;
        }
    }

    private boolean valid2=true;

    /**
     * dfs 深度优先,根据学习依赖关系,遍历进去是最后一个学习的
     * 所以这里找出来的学习顺序需要逆序
     * @param edges
     * @param visited
     * @param stack
     * @param index
     */
    private void dfs(List<List<Integer>> edges,int[] visited,Stack<Integer> stack,int index){
        visited[index]=1;
        for(Integer next:edges.get(index)){
            if(!valid2){
                return;
            }
            if(visited[next]==0){
                dfs(edges,visited,stack,next);
            }else if(visited[next]==1){
//                出现环,不可能拓扑排序
                valid2=false;
                return;
            }else{
//                已经访问过
            }
        }
        visited[index]=2;
//        此时 节点index的前驱已经都被访问了
        stack.push(index);
    }

    /**
     * 使用bfs方式遍历 拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder3(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges=new ArrayList<>(numCourses);
//        入度,据此判断可以开始的节点
        int[] ingress=new int[numCourses];
        int[] result=new int[numCourses];
        for (int i=0;i<numCourses;i++){
            edges.add(new ArrayList<>());
        }
        for (int[] edge:prerequisites){
            edges.get(edge[1]).add(edge[0]);
            ingress[edge[0]]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(ingress[i]==0){
                queue.offer(i);
            }
        }
        int tmp=0;
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            result[tmp]=poll;
            tmp++;
            for(Integer next:edges.get(poll)){
                ingress[next]--;
                if(ingress[next]==0){
                    queue.offer(next);
                }
            }
        }
        if(tmp<numCourses){
            return new int[]{};
        }else{
            return result;
        }
    }

    public static void main(String[] args){
        No210_findOrder test=new No210_findOrder();
//        int numCourses=2;
//        int[][] prerequisites={{0,1},{1,0}};

        int numCourses=4;
        int[][] prerequisites={{1,0},{2,0},{3,1},{3,2}};


        int[] order = test.findOrder(numCourses, prerequisites);
        CommonUtil.display(order);

        int[] order2 = test.findOrder2(numCourses, prerequisites);
        CommonUtil.display(order2);

        int[] order3 = test.findOrder3(numCourses, prerequisites);
        CommonUtil.display(order3);
    }
}
