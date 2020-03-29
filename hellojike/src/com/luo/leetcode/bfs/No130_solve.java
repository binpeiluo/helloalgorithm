package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class No130_solve {

    /**
     * 之前使用过union-find解决此问题,大概思路是将最外边的两行和两列中的字符O使用特殊字符#替换
     * 然后构建一个union-find连通性图,将与最外边的#相接的字符O替换成#,构建完毕后将剩下的字符O替换成X,接着把#替换成O即可
     *
     * 这种思路也可以使用bfs实现或者使用dfs实现
     * 使用dfs递归实现
     * @param board
     */
    public void solve(char[][] board) {
        int r=board.length;
        if(r<=2)
            return;
        int c=board[0].length;
        if(c<=2)
            return;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                boolean edge=i==0||i==r-1||j==0||j==c-1;
                if(edge&&board[i][j]=='O'){
                    helper(board,r,c,i,j);
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board[i][j]=='O')
                    board[i][j]='X';
                if(board[i][j]=='#')
                    board[i][j]='O';
            }
        }

    }
    private void helper(char[][] board,int r,int c,int row,int column){
//        注意结束条件
        if(row<0||row>=r||column<0||column>=c||board[row][column]=='X'||board[row][column]=='#')
            return;
        if(board[row][column]=='O')
            board[row][column]='#';
        helper(board,r,c,row-1,column);
        helper(board,r,c,row+1,column);
        helper(board,r,c,row,column-1);
        helper(board,r,c,row,column+1);
    }

    /**
     * 使用dfs 非递归实现
     * @param board
     */
    public void solve2(char[][] board) {
        int r=board.length;
        if(r<=2)
            return;
        int c=board[0].length;
        if(c<=2)
            return;
        Stack<int[]> stack=new Stack<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                boolean egde=i==0||i==r-1||j==0||j==c-1;
                if(egde && board[i][j]=='O')
                    stack.add(new int[]{i,j});
            }
        }
        int[][] direct={{1,0},{-1,0},{0,1},{0,-1}};
        while(!stack.isEmpty()){
            int[] pop = stack.pop();
            board[pop[0]][pop[1]]='#';
            for (int i = 0; i < direct.length; i++) {
                int x=pop[0]+direct[i][0];
                if(x<0||x>=r)
                    continue;
                int y=pop[1]+direct[i][1];
                if(y<0||y>=c)
                    continue;
                if(board[x][y]!='O')
                    continue;
                stack.push(new int[]{x,y});
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board[i][j]=='O')
                    board[i][j]='X';
                if(board[i][j]=='#')
                    board[i][j]='O';
            }
        }
    }

    public static void main(String[] args){
        No130_solve test=new No130_solve();
        char[][] board={
                {'O','X','X','O','X'},
                {'X','O','O','X','O'},
                {'X','O','X','O','X'},
                {'O','X','O','O','O'},
                {'X','X','O','X','O'}
        };
        test.solve(board);

//        test.solve2(board);

        CommonUtil.display(board);
    }
}
