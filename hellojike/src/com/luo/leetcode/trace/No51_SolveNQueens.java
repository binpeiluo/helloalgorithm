package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;

/*
    51. N皇后
    n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
        上图为 8 皇后问题的一种解法。
        给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
        每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
        示例:
        输入: 4
        输出: [
        [".Q..",  // 解法 1
        "...Q",
        "Q...",
        "..Q."],

        ["..Q.",  // 解法 2
        "Q...",
        "...Q",
        ".Q.."]
        ]
        解释: 4 皇后问题存在两个不同的解法。
*/
public class No51_SolveNQueens {

    private static final char NORMAL='.';
    private static final char QUEUE ='Q';

    /**
     * 思路:
     *      很典型的回溯算法
     *      对于每一行,遍历所有column,计算是否符合要求,如果符合则将皇后安置于此.接着回溯.
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board=new char[n][n];
//        构建棋盘
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                board[i][j]=NORMAL;
            }
        }
        List<List<String>> result=new ArrayList<>();
        helper(0,n,result,board);
        return result;
    }

    private void helper(int row,int n,List<List<String>> result,char[][] board){
        if(row==n){
            result.add(calcSolve(board));
            return;
        }
        for (int column = 0; column < n; column++) {
            if(isOk(row,column,board)){
                board[row][column]=QUEUE;
                helper(row+1,n,result,board);
                board[row][column]=NORMAL;
            }
        }
    }

    private List<String> calcSolve(char[][] board){
        List<String> result=new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            result.add(String.valueOf(board[i]));
        }
        return result;
    }

    private boolean isOk(int row,int column,char[][] board){
        for (int i = row; i >=0 ; i--) {
            char[] line = board[i];
            if(line[column]==QUEUE)
                return false;
            if(column-(row-i)>=0&&line[column-(row-i)]==QUEUE)
                return false;
            if(column+(row-i)<board.length&&line[column+(row-i)]==QUEUE)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        No51_SolveNQueens test=new No51_SolveNQueens();
        List<List<String>> lists = test.solveNQueens(4);
        System.out.println(lists);
    }


}
