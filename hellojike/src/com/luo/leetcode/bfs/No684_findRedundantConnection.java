package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.*;

/**
 * 684. 冗余连接
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
 * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。
 * 每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。
 * 如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * 注意:
 *
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *
 */
@SuppressWarnings("Duplicates")
public class No684_findRedundantConnection {

    /**
     * naive想法是使用dfs找出环出现的边
     * 但是这种方式找出来的是构成环的第一条边,并不是题目要求的返回最后出现一条边
     * 所以需要一遍构建一遍查找是否有环
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int len=edges.length;
//        构建连接表
        List<List<Integer>> graph=new ArrayList<>();
        for (int i = 0; i <=len; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e:edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        dfs(graph,new HashSet<>(),1,null);
        return edge;
    }

    private boolean found=false;
    private int[] edge;
    /**
     *
     * @param graph     邻接表
     * @param traceSet  路径
     * @param start     起始点
     * @param parent    该起始点的上一个节点,因为是无向图,需要排除上一个节点
     */
    private void dfs(List<List<Integer>> graph, Set<Integer> traceSet, Integer start, Integer parent){
        if(found){
            return;
        }
        traceSet.add(start);
        for (Integer next:graph.get(start)) {
            if(next==parent){
                continue;
            }
            if(traceSet.contains(next)){
//                发现成环, 冗余边是 start,next
                found=true;
                edge=new int[]{start,next};
                break;
            }else{
                dfs(graph,traceSet,next,start);
            }
        }
        traceSet.remove(start);
    }

    /**
     * 既然已经知道了上面一种方法不合适,改一下思路,一边构建一边判断
     *
     * 时间复杂度:   O(n^2)
     * 空间复杂度:   O(n)
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection2(int[][] edges) {
        Map<Integer,List<Integer>> graph=new HashMap<>();
        for (int[] edge:edges){
            int from=edge[0];
            int to=edge[1];
//            还没有加该条路径前已经存在其他路劲可以从from访问到to,那么当前要添加的边肯定是多余的
            if(dfs2(graph,new HashSet<>(),from,to)){
                return edge;
            }else{
                graph.putIfAbsent(from,new ArrayList<>());
                graph.get(from).add(to);
                graph.putIfAbsent(to,new ArrayList<>());
                graph.get(to).add(from);
            }
        }
        return null;
    }

    /**
     * 从图中找出是否有另一条路径可以从from走到to
     * @param graph
     * @param from
     * @param to
     * @return
     */
    private boolean dfs2(Map<Integer,List<Integer>> graph,Set<Integer> traceSet,
                         int from ,int to){
        if(!graph.containsKey(from)||!graph.containsKey(to)){
            return false;
        }
        if(from==to){
            return true;
        }
        traceSet.add(from);
        for (Integer next:graph.get(from)){
            if(traceSet.contains(next)){
                continue;
            }
            boolean canNext2To=dfs2(graph,traceSet,next,to);
            if(canNext2To){
                return true;
            }
        }
        traceSet.remove(from);
        return false;
    }

    /**
     * 查并集
     *
     * 时间复杂度:   O(n)
     * 空间复杂度:   O(n)
     * @param edges
     * @return
     */
    public int[] findRedundantConnection3(int[][] edges) {
        int len=edges.length;
        int[] parent=new int[len+1];
        int[] ranks=new int[len+1];
        for (int i = 0; i <=len; i++) {
            parent[i]=i;
            ranks[i]=1;
        }
        for (int[] edge:edges){
            int from=edge[0];
            int to=edge[1];
            if(union3(parent,ranks,from,to)){
                return new int[]{from,to};
            }
        }
        return null;
    }

    private boolean union3(int[] parent,int[] ranks,int i,int j){
        int rootI=root(parent,i);
        int rootJ=root(parent,j);
        if(rootI==rootJ){
//            本来就联通
            return true;
        }
        if(ranks[rootI]>ranks[rootJ]){
            parent[rootJ]=rootI;
        }else if(ranks[rootI]<ranks[rootJ]){
            parent[rootI]=rootJ;
        }else{
//            高度一致,需要增加ranks
            parent[rootJ]=rootI;
            ranks[rootI]++;
        }
        return false;
    }

    private int root(int[] parent,int i){
        if(parent[i]!=i){
            return root(parent,parent[i]);
        }else {
            return i;
        }
    }


    /**
     * 其他人的思路
     * @param edges
     * @return
     */
    public int[] findRedundantConnectionOther(int[][] edges) {
        // 使用邻接表存储图的信息
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // 遍历每一条边
        for(int[] edge : edges) {
            // Each element of edges is a pair [u, v] with u < v
            int u = edge[0];
            int v = edge[1];
            // 深度优先遍历该图，判断u到v之间是否已经存在了一条路径
            boolean hasPath = dfsOther(graph, u, v, new ArrayList<Integer>());
            if(hasPath == true) {
                return edge;
            }
            else {
                // 将该边加入到邻接表中
                if(!graph.containsKey(u)) {
                    graph.put(u, new ArrayList<Integer>());
                }
                graph.get(u).add(v);

                if(!graph.containsKey(v)) {
                    graph.put(v, new ArrayList<Integer>());
                }
                graph.get(v).add(u);
            }
        }
        return null;
    }
    // 深度优先遍历该图，判断start到end之间是否已经存在了一条路径
    private boolean dfsOther(Map<Integer, List<Integer>> graph, int start, int end, List<Integer> visited) {
        if(!graph.containsKey(start) || !graph.containsKey(end)) {
            return false;
        }
        if(start == end) {
            return true;
        }
        visited.add(start);
        // 遍历start的所有相邻节点
        for(int adj : graph.get(start)) {
            if(!visited.contains(adj)) {
                if(dfsOther(graph, adj, end, visited) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        No684_findRedundantConnection test=new No684_findRedundantConnection();
        int[][] edges=
                {
//                        {1,2},
//                        {1,3},
//                        {2,3}

                        {1,2},
                        {2,3},
                        {3,4},
                        {4,5},
                        {5,2}
                };

        int[] redundantConnectionOther = test.findRedundantConnectionOther(edges);
        CommonUtil.display(redundantConnectionOther);

        int[] redundantConnection = test.findRedundantConnection(edges);
        CommonUtil.display(redundantConnection);

        int[] redundantConnection2 = test.findRedundantConnection2(edges);
        CommonUtil.display(redundantConnection2);

        int[] redundantConnection3 = test.findRedundantConnection3(edges);
        CommonUtil.display(redundantConnection3);

    }
}
