package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.LinkedList;
import java.util.Queue;
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
@SuppressWarnings("Duplicates")
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
        if(row<0||row>=r||column<0||column>=c
                ||board[row][column]=='X'
                ||board[row][column]=='#')
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

    /**
     * bfs 思路就是将边缘的O联通的所有O全部置换为特殊字符
     * 然后将剩下的所有O再替换成X,最后将特殊字符还原
     * @param board
     */
    public void solve3(char[][] board) {
        int m=board.length;
        if(m<=2){
            return;
        }
        int n=board[0].length;
        if(n<=2){
            return;
        }
        int[][] direct={{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited=new boolean[m][n];
        Queue<int[]> queue=new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if(board[i][0]=='O'){
                queue.offer(new int[]{i,0});
                visited[i][0]=true;
            }
            if(board[i][n-1]=='O'){
                queue.offer(new int[]{i,n-1});
                visited[i][n-1]=true;
            }
        }
        for (int i = 0; i < n; i++) {
            if(board[0][i]=='O'){
                queue.offer(new int[]{0,i});
                visited[0][i]=true;
            }
            if(board[m-1][i]=='O'){
                queue.offer(new int[]{m-1,i});
                visited[m-1][i]=true;
            }
        }
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            if(board[poll[0]][poll[1]]=='O'){
                board[poll[0]][poll[1]]='#';
                for (int i = 0,len=direct.length; i < len; i++) {
                    int x=direct[i][0]+poll[0];
                    if(x<0||x>=m){
                        continue;
                    }
                    int y=direct[i][1]+poll[1];
                    if(y<0||y>=n){
                        continue;
                    }
                    if(visited[x][y]||board[x][y]!='O'){
                        continue;
                    }
                    queue.offer(new int[]{x,y});
                    visited[x][y]=true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }

    public static void main(String[] args){
        No130_solve test=new No130_solve();
        char[][] board={
//                {'O','X','X','O','X'},
//                {'X','O','O','X','O'},
//                {'X','O','X','O','X'},
//                {'O','X','O','O','O'},
//                {'X','X','O','X','O'}

                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'},

        };
//        test.solve(board);

//        test.solve2(board);
        test.solve3(board);

        CommonUtil.display(board);
    }
}
