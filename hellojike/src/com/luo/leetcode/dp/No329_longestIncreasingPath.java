package com.luo.leetcode.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2:
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 */
@SuppressWarnings("Duplicates")
public class No329_longestIncreasingPath {

    /**
     * 动态规划
     * 令dp[i][j][m][n] 表示从 matrix[i][j]到matrix[m][n] 之间的最大递增长度
     * 状态转移方程 前进到matrix[m][n] 有四个位置 [m-1][n],[m+1][n],[m][n-1],[m][n+1]
     *  当 matrix[m-1][n] 小于 matrix[m][n] 时, 从matrix[i][j]到matrix[m][n]可以是 dp[i][j][m-1][n]+1
     *  当 matrix[m+1][n] 小于 matrix[m][n] 时, 从matrix[i][j]到matrix[m][n]可以是 dp[i][j][m+1][n]+1
     *  当 matrix[m][n-1] 小于 matrix[m][n] 时, 从matrix[i][j]到matrix[m][n]可以是 dp[i][j][m][n-1]+1
     *  当 matrix[m][n+1] 小于 matrix[m][n] 时, 从matrix[i][j]到matrix[m][n]可以是 dp[i][j][m][n+1]+1
     * 上述这四种情况,取最大值
     * 但问题是这里dp[i][j][m][n] 依赖于 dp[i][j][m-1][n],dp[i][j][m+1][n],dp[i][j][m][n-1],dp[i][j][m][n+1]
     * 一个值依赖于相邻的四个方向的值,计算需要按照什么方向
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        return 0;
    }

    /**
     * bfs
     * 对于每个位置,使用广度优先遍历四个方向的位置,计算处以该位置为起点的最长递增路径长度
     *
     * 自己找不到优化方向,看了官方可以遍历的起点开始.可以根据拓扑结构找出出度为0的位置(代表周围没有比自己小的元素)
     *
     * 时间复杂度: O(m*n*m)
     * 空间复杂度: O(m*n)
     * @param matrix
     * @return
     */
    public int longestIncreasingPath2(int[][] matrix) {
        int result=0;
        int m=matrix.length;
        if(m==0){
            return 0;
        }
        int n=matrix[0].length;
        if(n==0){
            return 0;
        }
        int[][] direct={{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] visited=new boolean[m][n];
//        貌似因为缓存有点复杂,控制循环跳转位置
//        设定cache[i][j]为以位置[i,j]为起点的最长路径长度
        int[][] cache=new int[m][n];
        Queue<int[]> queue=new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]){
                    continue;
                }
//                将辅助数组复原
                queue.clear();
                for (int k = 0; k < m; k++) {
                    Arrays.fill(visited[k],false);
                }
                int res=0;
                queue.offer(new int[]{i,j});
                visited[i][j]=true;
                while(!queue.isEmpty()){
                    int size = queue.size();
                    res++;
                    for (int k = 0; k < size; k++) {
                        int[] poll = queue.poll();
                        for (int l = 0; l < direct.length; l++) {
                            int x=poll[0]+direct[l][0];
                            if(x<0||x>=m){
                                continue;
                            }
                            int y=poll[1]+direct[l][1];
                            if(y<0||y>=n){
                                continue;
                            }
                            if(visited[x][y]||matrix[x][y]<=matrix[poll[0]][poll[1]]){
                                continue;
                            }
                            queue.offer(new int[]{x,y});
                        }
                    }
                }
                result=Math.max(result,res);
            }
        }
        return result;
    }

    /**
     * dfs 递归版本
     * 时间复杂度: O(m*n) 因为使用了缓存
     * 空间复杂度: O(m*m)
     * @param matrix
     * @return
     */
    public int longestIncreasingPath3(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int m=matrix.length;
        int n=matrix[0].length;
        int result=0;
        int[][] cache=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result=Math.max(result,dfs3(matrix,cache,i,j));
            }
        }
        return result;
    }

    private int[][] direct={{0,1},{1,0},{0,-1},{-1,0}};
    private int dfs3(int[][] matrix,int[][] cache,int i,int j){
        if(cache[i][j]!=0){
            return cache[i][j];
        }
//        此时就缓存路径长度
        cache[i][j]++;
        for (int k = 0; k < direct.length; k++) {
            int x=i+direct[k][0];
            int y=j+direct[k][1];
            if(x<0||x>=matrix.length||y<0||y>=matrix[0].length||matrix[x][y]<=matrix[i][j]){
               continue;
            }
            cache[i][j]=Math.max(cache[i][j],dfs3(matrix,cache,x,y)+1);
        }
        return cache[i][j];
    }


    public int longestIncreasingPath4(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
//        出度,记录周围有多少元素比自己大
        int[][] outdegrees = new int[rows][columns];
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0], newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows
                            && newColumn >= 0 && newColumn < columns
                            && matrix[newRow][newColumn] > matrix[i][j]) {
                        ++outdegrees[i][j];
                    }
                }
            }
        }
//       将出度为0的位置入队列,周围没有比自己大的
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (outdegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0], newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows
                            && newColumn >= 0 && newColumn < columns
                            && matrix[newRow][newColumn] < matrix[row][column]) {
//                        为什么这么处理?? 为什么需要等待该位置出度为0时才入队
                        --outdegrees[newRow][newColumn];
                        if (outdegrees[newRow][newColumn] == 0) {
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        No329_longestIncreasingPath test=new No329_longestIncreasingPath();
//        int[][] matrix={
//                {9,9,4},
//                {6,6,8},
//                {2,2,1}
//        };

//        int[][] matrix={
//                {3,4,5},
//                {3,2,6},
//                {2,2,1}
//        };


        int[][] matrix={
                {0,1,2,3,4,5,6,7,8,9},
                {19,18,17,16,15,14,13,12,11,10},
                {20,21,22,23,24,25,26,27,28,29},
                {39,38,37,36,35,34,33,32,31,30},
                {40,41,42,43,44,45,46,47,48,49},
                {59,58,57,56,55,54,53,52,51,50},
                {60,61,62,63,64,65,66,67,68,69},
                {79,78,77,76,75,74,73,72,71,70},
                {80,81,82,83,84,85,86,87,88,89},
                {99,98,97,96,95,94,93,92,91,90},
                {100,101,102,103,104,105,106,107,108,109},
                {119,118,117,116,115,114,113,112,111,110},
                {120,121,122,123,124,125,126,127,128,129},
                {139,138,137,136,135,134,133,132,131,130},
                {0,0,0,0,0,0,0,0,0,0}
        };


        int i1 = test.longestIncreasingPath3(matrix);
        System.out.println(i1);

//        int i = test.longestIncreasingPath2(matrix);
//        System.out.println(i);

        int i2 = test.longestIncreasingPath4(matrix);
        System.out.println(i2);

    }

}
