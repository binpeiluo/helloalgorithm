package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.*;

/**
 * 417. 太平洋大西洋水流问题
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 提示：
 *  输出坐标的顺序不重要
 *  m 和 n 都小于150
 * 示例：
 * 给定下面的 5x5 矩阵:
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 */
public class No417_pacificAtlantic {


    /**
     * 自己的navie想法是两次bfs,找到能抵达太平洋和大西洋的坐标,然后取交集
     * @param matrix
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result=new ArrayList<>();
        int r=matrix.length;
        if(r==0)
            return result;
        int c=matrix[0].length;
        if(c==0)
            return result;
        Queue<int[]> queue=new LinkedList<>();
        int[][] visited=new int[r][c];
        for (int i = 0; i < c; i++) {
            if((visited[0][i]&1)==1)
                continue;
            queue.offer(new int[]{0,i});
            visited[0][i]|=1;
        }
        for (int i = 0; i < r; i++) {
            if((visited[i][0]&1)==1)
                continue;
            queue.offer(new int[]{i,0});
            visited[i][0]|=1;
        }
        int[][] direct={{0,1},{1,0},{-1,0},{0,-1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < direct.length; j++) {
                    int x=poll[0]+direct[j][0];
                    if(x<0||x>=r)
                        continue;
                    int y=poll[1]+direct[j][1];
                    if(y<0||y>=c)
                        continue;
                    if(visited[x][y]==1)
                        continue;
                    if(matrix[x][y]<matrix[poll[0]][poll[1]])
                        continue;
                    visited[x][y]|=1;
                    queue.offer(new int[]{x,y});
                }
            }
        }
        CommonUtil.display(visited);
        for (int i = 0; i < c; i++) {
            if ((visited[r-1][i]&2)==2)
                continue;
            visited[r-1][i]|=2;
            queue.offer(new int[]{r-1,i});
        }
        for (int i = 0; i < r; i++) {
            if ((visited[i][c-1]&2)==2)
                continue;
            visited[i][c-1]|=2;
            queue.offer(new int[]{i,c-1});
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < direct.length; j++) {
                    int x=poll[0]+direct[j][0];
                    if(x<0||x>=r)
                        continue;
                    int y=poll[1]+direct[j][1];
                    if(y<0||y>=c)
                        continue;
                    if((visited[x][y]&2)==2)
                        continue;
                    if(matrix[x][y]<matrix[poll[0]][poll[1]])
                        continue;
                    visited[x][y]|=2;
                    queue.offer(new int[]{x,y});
                }
            }
        }
        CommonUtil.display(visited);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if((visited[i][j]&3)==3)
                    result.add(Arrays.asList(i,j));
            }
        }
        return result;
    }

    public static void main(String[] args){
        No417_pacificAtlantic test=new No417_pacificAtlantic();
//        int[][] matrix={
//                {1,	2,	2,	3,	5},
//                {3,	2,	3,	4,	4},
//                {2,	4,	5,	3,	1},
//                {6,	7,	1,	4,	5},
//                {5,	1,	1,	2,	4}
//        };

        int[][] matrix={
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };
        List<List<Integer>> lists = test.pacificAtlantic(matrix);
        System.out.println(lists);
    }
}
