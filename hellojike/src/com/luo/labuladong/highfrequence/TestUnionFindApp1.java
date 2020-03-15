package com.luo.labuladong.highfrequence;

import com.luo.util.CommonUtil;

/**
 * union-find应用问题
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class TestUnionFindApp1 {
    /**
     * 不使用uf解决的话,最简单方法就是从四个方向使用for循环,将外边的0替换成一个特殊字符#
     * 然后在遍历二维数组,将其中的O替换成X,最后再将外层的#替换成0
     *
     * 本题中输入是一个二维数组,uf中的是一个一维数组,这可以通过计算转换
     * 问题的关键在于如何抽象,如何将该问题抽象成连通性问题.
     * 我们可以把这种和边界的O的祖先设置成同一个,这样子就可以对每个字符判别
     *
     * @param board
     */
    public void solve(char[][] board) {
        int m=board.length;
        if(m==0)
            return ;
        int n=board[0].length;
        if (n==0)
            return ;
//        预留一个位置,代表是与边界相交的
        int temp = m * n;
        TestUnionFind.UF uf=new TestUnionFind.UF(temp +1);
//        二维坐标转一维坐标公式 [x,y]=x*n+y
//        判断最外边的两行两列
        for (int i = 0; i < m; i++) {
            if(board[i][0]=='O'){
                uf.union(i*n+0, temp);
            }
            if(board[i][n-1]=='O'){
                uf.union(i*n+n-1, temp);
            }
        }
        for (int i = 0; i < n; i++) {
            if(board[0][i]=='O'){
                uf.union(0*n+i, temp);
            }
            if(board[m-1][i]=='O'){
                uf.union((m-1)*n+i, temp);
            }
        }
//        遍历二维数组
        int[][] direct={{1,0},{-1,0},{0,1},{0,-1}};
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
//                判断四个方向
                if(board[i][j]=='O'){
                    for (int k = 0; k < direct.length; k++) {
                        int x=i+direct[k][0];
                        int y=j+direct[k][1];
                        if(board[x][y]=='O'){
                            uf.union(x*n+y,i*m+j);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]=='O'){
                    board[i][j]=uf.connected(i*n+j, temp)?'O':'X';
                }
            }
        }
    }


    public static void main(String[] args){
        TestUnionFindApp1 test=new TestUnionFindApp1();
        char[][] board={
                {'O','X','X','O','X'},
                {'X','O','O','X','O'},
                {'X','O','X','O','X'},
                {'O','X','O','O','O'},
                {'X','X','O','X','O'}
        };

        test.solve(board);
        CommonUtil.display(board);
    }
}
