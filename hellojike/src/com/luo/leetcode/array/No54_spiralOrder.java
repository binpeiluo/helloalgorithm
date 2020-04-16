package com.luo.leetcode.array;

/*
    54. 螺旋矩阵
    给定一个包含 m x n 个元素的矩阵（m 行, n 列），
    请按照顺时针螺旋顺序，返回矩阵中的所有元素。
    示例 1:
    输入:
    [
    [ 1, 2, 3 ],
    [ 4, 5, 6 ],
    [ 7, 8, 9 ]
    ]
    输出: [1,2,3,6,9,8,7,4,5]
    示例 2:
    输入:
    [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9,10,11,12]
    ]
    输出: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

import java.util.ArrayList;
import java.util.List;

public class No54_spiralOrder {
    /**
     * 一层层遍历
     * 边界条件实在有点难以控制.
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result=new ArrayList<>();
        int r=matrix.length;
        if(r==0)
            return result;
        int c=matrix[0].length;
        if(c==0)
            return result;
//        层数的计算,假如3列,那需要遍历2层.加入2列只需要遍历1层.故需要将行列都减一再计算层数
        int levelCount=Math.min(r-1,c-1)/2;
        for (int i = 0; i <=levelCount; i++) {
            helper(result,matrix,i);
        }
        return result;

    }

    private void helper(List<Integer> result,int[][] matrix,int level){
        int r=matrix.length;
        int c=matrix[0].length;
        int i=level,j=level;
//        当只剩下一个元素,那打印这个元素即可
        if(j==c-1-level && i==r-1-level){
            result.add(matrix[i][j]);
            return;
        }
//        假如只有一列
        if(j==c-1-level){
            for (;i<r-level;i++)
                result.add(matrix[i][j]);
            return;
        }
//        假如只有一行
        if(i==r-1-level){
            for (;j<c-level;j++)
                result.add(matrix[i][j]);
            return;
        }
        for (;j<c-1-level;j++)
            result.add(matrix[i][j]);
        for (;i<r-1-level;i++)
            result.add(matrix[i][j]);
        for (;j>level;j--)
            result.add(matrix[i][j]);
        for (;i>level;i--)
            result.add(matrix[i][j]);
    }

    /**
     * 上边的思路导致遍历有点复杂.因为按一层的遍历是从 (0,0)->(0,c-1) 然后 (0,c)->(r-1,c)
     * 可以考虑从左向右遍历一行时,取到整行数据,然后从上到下遍历一列时,从1遍历到最后.
     * 如此避免当遍历的层只有一行或者一列时造成数据丢失或者重复
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result=new ArrayList<>();
        int r=matrix.length;
        if(r==0)
            return result;
        int c=matrix[0].length;
        int startR=0,startC=0,endR=r-1,endC=c-1;
        while(startR<=endR && startC <= endC){
            for (int currC=startC;currC<=endC;currC++)
                result.add(matrix[startR][currC]);
            for (int currR = startR+1; currR <=endR ; currR++)
                result.add(matrix[currR][endC]);
            if(endR-startR>0&&endC-startC>0){
                for (int currC = endC-1; currC >startC; currC--)
                    result.add(matrix[endR][currC]);
                for (int currR = endR; currR >startR ; currR--)
                    result.add(matrix[currR][startC]);
            }
            startR++;
            startC++;
            endC--;
            endR--;
        }
        return result;
    }

    public static void main(String[] args){
        No54_spiralOrder test=new No54_spiralOrder();
//        int[][] matrix={
//                { 1, 2, 3 },
//                { 4, 5, 6 },
//                { 7, 8, 9 }
//        };

//        int[][] matrix={
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9,10,11,12}
//        };

//        int[][] matrix={
//                {1,2,3,4,5,6,7,8,9,10},
//                {11,12,13,14,15,16,17,18,19,20}
//        };

//        int[][] matrix={{1,2,3,4}};

        int[][] matrix={
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        List<Integer> integers = test.spiralOrder(matrix);
        System.out.println(integers);

        List<Integer> integers1 = test.spiralOrder2(matrix);
        System.out.println(integers1);
    }
}
