package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/**
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。
 * 请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 */
public class No0107_rotate {

    /**
     * 旋转公式:(x,y)->(y,n-1-x)
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len=matrix.length;
        if(len<=1)
            return;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                exch(matrix,len,i,j);
            }
        }
    }

    private void exch(int[][] matrix,int n,int x,int y){
        int temp=matrix[x][y];
        matrix[x][y]=matrix[y][n-1-x];
        matrix[y][n-1-x]=temp;
    }

    public static void main(String[] args){
        No0107_rotate test=new No0107_rotate();
        int[][] matrix={
                        {7,4,1},
                        {8,5,2},
                        {9,6,3}
                };
        test.rotate(matrix);
        CommonUtil.display(matrix);
    }
}
