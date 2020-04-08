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
     * 果然太navie,对于每个元素使用旋转公式反转其实完成不了,因为遍历是从上到下,从左到右.有的元素已经在正确位置了但还会继续旋转
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

    /**
     * 可以先经左上右下对角线进行翻转,然后再对每行倒序
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix){
        int len=matrix.length;
        if(len<=1)
            return;
//        旋转公式,(x,y)->(y,x)
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <len / 2; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][len-1-j];
                matrix[i][len-1-j]=temp;
            }
        }
    }


    public static void main(String[] args){
        No0107_rotate test=new No0107_rotate();
        int[][] matrix={
                        {7,4,1},
                        {8,5,2},
                        {9,6,3}
                };
        test.rotate2(matrix);
        CommonUtil.display(matrix);
    }
}
