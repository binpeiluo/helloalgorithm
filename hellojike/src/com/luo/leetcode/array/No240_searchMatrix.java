package com.luo.leetcode.array;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 */
public class No240_searchMatrix {

    /**
     * 自己想到的navie方法只有暴力算法,真的菜
     * 看到一种思路是观察数组,数组从上到下,从左到右增大.那么从左下角开始观察,遍历两个方法,向上则变小,向右则变大,这不就解出来了吗...  真的厉害
     * 这种解法的时间复杂度: O(m+n) 空间复杂度:O(1)
     *
     * 另外,有序数组一般就应该想到二分.可以在矩阵找出中位点,以此中间点将一个矩阵分为四个.并且判断中位点的值是否等于target.
     * 假如小于target,那么target应该在剩下的三个矩阵中.但实现起来有点复杂
     * 这种解法的复杂度: O(logm*logn) 空间复杂度:O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int r=matrix.length;
        if(r==0)
            return false;
        int c=matrix[0].length;
        if(c==0)
            return false;
        int x=r-1,y=0;
        while(true){
            if(matrix[x][y]==target)
                return true;
            if(matrix[x][y]<target){
                y++;
                if(y==c)
                    return false;
            }else if(matrix[x][y]>target){
                x--;
                if(x<0)
                    return false;
            }
        }
    }

    public static void main(String[] args){
        No240_searchMatrix test=new No240_searchMatrix();
        int[][] matix={
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target=20;
        boolean b = test.searchMatrix(matix, target);
        System.out.println(b);

    }
}
