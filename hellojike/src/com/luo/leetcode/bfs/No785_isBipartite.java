package com.luo.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 785. 判断二分图
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，
 * 并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。
 * 每个节点都是一个在0到graph.length-1之间的整数。
 * 这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 *
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 *
 */
public class No785_isBipartite {


    private static final int UNREACH=0;
    private static final int RED=1;
    private static final int BLACK=2;
    /**
     * 题中提到将节点分为两个集合,每条边的一端来自一个集合,另一端来自另一个集合
     * 我们可以给一个集合划分为两种颜色,那么按照这种说法,相同颜色必然不会相间
     * 可以使用bfs或者dfs方式遍历,给每个节点涂色.遍历完没有冲突则说明可以二分
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
//        dfs方式
        int n=graph.length;
        int[] colors=new int[n];
        for(int i=0;i<n;i++){
//            已经涂过色的节点不需要再次进行深度遍历了.因为以该节点为起点的路径已经判断过了
            if(colors[i]==UNREACH&&!dfs(graph,colors,i,RED)){
                return false;
            }
        }
        return true;
    }

    /**
     * 定义还没有涂色的节点涂色的方法
     * @param graph
     * @param colors
     * @param index
     * @param color
     * @return
     */
    private boolean dfs(int[][] graph,int[] colors,int index,int color){
        colors[index]=color;
        int subNodeColor=color==RED?BLACK:RED;
        for (int node:graph[index]) {
            if(colors[node]==UNREACH){
//                维护这么一个条件,只有没有涂色的节点会递归,有颜色的节点判断是否违背相间的关系
                boolean subNodeResult=dfs(graph,colors,node,subNodeColor);
                if(!subNodeResult){
                    return false;
                }
            }else{
                if(colors[node] !=subNodeColor){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * bfs方式
     * @param graph
     * @return
     */
    public boolean isBipartite2(int[][] graph) {
        int n=graph.length;
        int[] colors=new int[n];
        Queue<Integer> queue=new LinkedList<>();
//        由于不知道图是否联通,所以每个节点都需要遍历
        for (int i = 0; i < n; i++) {
//            只遍历没有涂过色的节点,如果已经涂过色了,那么说明该该节点作为起点的广度遍历操作已经执行过了
            if(colors[i]==UNREACH){
                queue.clear();
//                已经涂过色才遍历
                queue.offer(i);
                colors[i]=RED;
                while(!queue.isEmpty()){
                    Integer poll = queue.poll();
                    int subNodeColor=colors[poll]==RED?BLACK:RED;
                    for (int node:graph[poll]){
                        if(colors[node]==UNREACH){
                            queue.offer(node);
                            colors[node]=subNodeColor;
                        }else{
                            if(colors[node]!=subNodeColor){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        No785_isBipartite test=new No785_isBipartite();
//        int[][] graph={{1,3}, {0,2}, {1,3}, {0,2}};

        int[][] graph={{1,2,3}, {0,2}, {0,1,3}, {0,2}};

        boolean bipartite = test.isBipartite(graph);
        System.out.println(bipartite);

        boolean bipartite2 = test.isBipartite2(graph);
        System.out.println(bipartite2);
    }
}
