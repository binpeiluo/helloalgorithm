package com.luo.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 */
public class No200_numIslands {

    /**
     * 很明显可以使用 union-find框架解决
     * 这里使用bfs实现
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int r=grid.length;
        if(r==0)
            return 0;
        int c=grid[0].length;
        if(c==0)
            return 0;
        boolean[][] visited=new boolean[r][c];
        Queue<int[]> queue=new LinkedList<>();
        int[][] direct={{-1,0},{1,0},{0,-1},{0,1}};
        int res=0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(!visited[i][j]&&grid[i][j]=='1'){
                    visited[i][j]=true;
                    queue.offer(new int[]{i,j});
                    while(!queue.isEmpty()){
                        int[] poll = queue.poll();
                        for (int k = 0; k < direct.length; k++) {
                            int x=poll[0]+direct[k][0];
                            if(x<0||x>=r)
                                continue;
                            int y=poll[1]+direct[k][1];
                            if(y<0||y>=c)
                                continue;
                            if(visited[x][y]||grid[x][y]=='0')
                                continue;
                            visited[x][y]=true;
                            queue.offer(new int[]{x,y});
                        }
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * dfs
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        int r=grid.length;
        if(r==0)
            return 0;
        int c=grid[0].length;
        if(c==0)
            return 0;
        boolean[][] visited=new boolean[r][c];
        int res=0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(!visited[i][j] && grid[i][j]=='1'){
                    visited[i][j]=true;
                    res++;
                    dfs2(grid,r,c,i,j,visited);
                }
            }
        }
        return res;
    }

    int[][] direct={{-1,0},{1,0},{0,-1},{0,1}};
    private void dfs2(char[][] grid,int r ,int c,int i,int j,boolean[][] visited){
        for (int k = 0; k < direct.length; k++) {
            int x=i+direct[k][0];
            if(x<0||x>=r)
                continue;
            int y=j+direct[k][1];
            if(y<0||y>=c)
                continue;
            if(visited[x][y]||grid[x][y]=='0')
                continue;
            visited[x][y]=true;
            dfs2(grid,r,c,x,y,visited);
        }
    }

    public static void main(String[] args){
        No200_numIslands test=new No200_numIslands();
        char[][] grid={

//                {'1','0','0','0','0'},
//                {'1','0','0','0','0'},
//                {'0','0','0','0','0'},
//                {'0','0','0','0','0'}

                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','1'},
                {'0','0','0','1','1'}

            };

        int i = test.numIslands(grid);
        System.out.println(i);

        int i1 = test.numIslands2(grid);
        System.out.println(i1);
    }
}
