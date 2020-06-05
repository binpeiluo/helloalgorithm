package com.luo.leetcode.array;
/*

面试题29. 顺时针打印矩阵
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 
限制：
0 <= matrix.length <= 100
0 <= matrix[i].length <= 100
*/

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class No_m29_spiralOrder {
    /**
     * 比较简单的思路是按层遍历
     *
     * 需要注意每一层是如何遍历的,
     *  1 left~right
     *  2 top+1~bottom
     *  3 right-1~left+1
     *  4 bottom-1~top+1
     * 按照这种逻辑遍历,当只有一行或者一列,会出现逻辑错误.
     * 所以需要在这四个步骤进行下去时,添加判断条件
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result=new ArrayList<>();
        int m=matrix.length;
        if(m==0)
            return result;
        int n=matrix[0].length;
        int left=0,right=n-1,top=0,bottom=m-1;
        while(left<=right&&top<=bottom){
            for (int i = left; i <=right; i++) {
                result.add(matrix[top][i]);
            }
            if(top<bottom){
                for (int i = top+1; i <=bottom; i++) {
                    result.add(matrix[i][right]);
                }

                if(left<right){
                    for (int i = right-1; i >left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    for (int i = bottom; i >top; i--) {
                        result.add(matrix[i][left]);
                    }
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    public int[] spiralOrder2(int[][] matrix){
        int m=matrix.length;
        if(m==0)
            return new int[0];
        int n=matrix[0].length;
        int len=m*n;
        int[] result=new int[len];
        int left=0,right=n-1,top=0,bottom=m-1,index=0;
        while(index<len){
            for (int i = left; i <=right; i++) {
                result[index++]=matrix[top][i];
            }
//            防止只有一行
            if(top<bottom){
                for (int i = top+1; i <=bottom; i++) {
                    result[index++]=matrix[i][right];
                }

//              防止只有一列
                if(left<right){
                    for (int i = right-1; i >left ; i--) {
                        result[index++]=matrix[bottom][i];
                    }
                    for (int i = bottom; i >top ; i--) {
                        result[index++]=matrix[i][left];
                    }
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    public static void main(String[] args){
        No_m29_spiralOrder test=new No_m29_spiralOrder();
//        int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12}};

//        int[][] matrix={{3},{2}};
//        int[][] matrix={{3,2}};
        int[][] matrix={{6,9,7}};
        List<Integer> integers = test.spiralOrder(matrix);
        System.out.println(integers);

        int[] ints = test.spiralOrder2(matrix);
        CommonUtil.display(ints);
    }
}
