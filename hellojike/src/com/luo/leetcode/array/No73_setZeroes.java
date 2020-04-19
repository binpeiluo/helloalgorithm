package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */
public class No73_setZeroes {

    /**
     * 思路一:
     *      使用m*n的额外空间,生成新的数组.遍历新数组,对于任意位置(i,j)需要在原数组遍历一行和一列判断是否有0
     *
     * 思路二:
     *      使用两个额外的list,用来储存需要置0的行和列.遍历原来数组,当发现任意位置所在行或者列在这两个list之中的话就置零
     *
     * 思路三:
     *      当发现某位位置为零时,将其行首和列首都标记为0,然后再遍历数组,当发现行首或者列首为零时,则设置该位置为零.
     *      但是首行和首列需要特殊处理
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int r=matrix.length;
        if(r<=0)
            return;
        int c=matrix[0].length;
        if(c<=0)
            return;
        boolean isRows=false,isCols=false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(i==0&&matrix[i][j]==0)
                    isRows=true;
                if(j==0&&matrix[i][j]==0)
                    isCols=true;
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if(matrix[0][j]==0||matrix[i][0]==0)
                    matrix[i][j]=0;
            }
        }

        if(isRows){
            for (int i = 0; i < c; i++) {
                matrix[0][i]=0;
            }
        }
        if(isCols){
            for (int i = 0; i < r; i++) {
                matrix[i][0]=0;
            }
        }
    }

    public static void main(String[] args){
        No73_setZeroes test=new No73_setZeroes();
//        int[][] matrix={
//          {0,1,2,0},
//          {3,4,5,2},
//          {1,3,1,5}
//        };

        int[][] matrix={{0,1}};

        test.setZeroes(matrix);

        CommonUtil.display(matrix);
    }
}
