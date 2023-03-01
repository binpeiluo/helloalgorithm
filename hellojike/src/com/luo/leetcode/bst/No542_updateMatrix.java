package com.luo.leetcode.bst;

/*
    542. 01 矩阵
    给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
    两个相邻元素间的距离为 1 。
    示例 1:
    输入:
    0 0 0
    0 1 0
    0 0 0
    输出:
    0 0 0
    0 1 0
    0 0 0

    示例 2:
    输入:
    0 0 0
    0 1 0
    1 1 1
    输出:
    0 0 0
    0 1 0
    1 2 1

    注意:
    给定矩阵的元素个数不超过 10000。
    给定矩阵中至少有一个元素是 0。
    矩阵中的元素只在四个方向上相邻: 上、下、左、右。
*/

import com.luo.util.CommonUtil;

import java.util.LinkedList;
import java.util.Queue;

public class No542_updateMatrix {
    /**
     * 典型bfs问题
     *
     * 时间复杂度:   O(n^3)
     * 空间复杂度:   O(n^2)
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        int r=matrix.length;
        int c=matrix[0].length;
        Queue<int[]> queue=new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(matrix[i][j]==1){
                    queue.clear();
                    queue.offer(new int[]{i,j});
                    matrix[i][j]=helper(matrix,queue);
                }
            }
        }
        return matrix;
    }

    private int[][] direct={{1,0},{-1,0},{0,-1},{0,1}};
    private int helper(int[][] matrix, Queue<int[]> queue){
        int res=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if(matrix[poll[0]][poll[1]]==0)
                    return res;
                for (int j = 0; j < direct.length; j++) {
                    int x=poll[0]+direct[j][0];
                    if(x<0||x>=matrix.length)
                        continue;
                    int y=poll[1]+direct[j][1];
                    if(y<0||y>=matrix[0].length)
                        continue;
                    queue.offer(new int[]{x,y});
                }
            }
            res++;
        }
        return 0;
    }

    public static void main(String[] args){
        No542_updateMatrix test=new No542_updateMatrix();
        int[][] matrix={
                {0, 0, 0},
                {0, 1, 0},
                {1, 2, 1}
        };
        test.updateMatrix(matrix);
        CommonUtil.display(matrix);
    }
}
