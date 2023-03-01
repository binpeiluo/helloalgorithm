package com.luo.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 */
public class No994_orangesRotting {

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue=new LinkedList<>();
        int normalCount=0;
        int r=grid.length;
        int c=grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }else if(grid[i][j]==1){
                    normalCount++;
                }
            }
        }
        int[][] direct={{0,1},{1,0},{0,-1},{-1,0}};
        int level=0;
        while(!queue.isEmpty()&&normalCount!=0){
            int size = queue.size();
            level++;
            for (int n = 0; n < size; n++) {
                int[] poll = queue.poll();
                for (int i = 0,len=direct.length; i < len; i++) {
                    int newR=poll[0]+direct[i][0];
                    if(newR<0||newR>=r)
                        continue;
                    int newC=poll[1]+direct[i][1];
                    if(newC<0||newC>=c)
                        continue;
                    if(grid[newR][newC]==1){
                        normalCount--;
                        grid[newR][newC]=2;
                        queue.offer(new int[]{newR,newC});
                    }
                }
            }
        }
        if(normalCount!=0)
            return -1;
        else
            return level;
    }

    public static void main(String[] args){
        No994_orangesRotting test=new No994_orangesRotting();
        int[][] orange={{2,1,1},{1,1,0},{0,1,1}};
        int i = test.orangesRotting(orange);
        System.out.println(i);
    }
}
