package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *
 */
public class No16_generateMatrix {

    /**
     * 自己的想法是模拟螺旋
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix=new int[n][n];
        int currentNum=0;
        int limit=n*n;
        int[][] direction={{0,1},{1,0},{0,-1},{-1,0}};
        int row=0,column=0;
        int directionIndex=0;
        while(++currentNum<=limit){
//            打印了当前数字
            matrix[row][column]=currentNum;
//            继续往某个方向前进.直到到尽头
            int nextRow=row+direction[directionIndex][0];
            int nextColumn=column+direction[directionIndex][1];
//            如果越界了,换个方向
            if(nextRow<0||nextRow>=n||nextColumn<0||nextColumn>=n||matrix[nextRow][nextColumn]>0){
                directionIndex=(directionIndex+1)%4;
            }
//            更新坐标
            row=row+direction[directionIndex][0];
            column=column+direction[directionIndex][1];

        }
        return matrix;
    }


    public static void main(String[] args) {
        No16_generateMatrix test=new No16_generateMatrix();
        int n=3;
        int[][] ints = test.generateMatrix(n);
        CommonUtil.display(ints);
    }
}
