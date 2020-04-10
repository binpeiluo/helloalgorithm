package com.luo.leetcode.trace;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 */
public class No79_exist {

    /**
     * 使用dfs 回溯
     * 对于每个起点,使用dfs回溯
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int r=board.length;
        int c=board[0].length;
        boolean[][] visited=new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(helper(board,visited,word,0,i,j))
                    return true;
            }
        }
        return false;
    }

    private int[][] direct={{1,0},{0,1},{-1,0},{0,-1}};
    private boolean helper(char[][] board,boolean[][] vistied,String word,int wIndex,int r,int c){
        if(wIndex==word.length())
            return true;
        int rLen=board.length;
        int cLen=board[0].length;
        if(board[r][c]!=word.charAt(wIndex))
            return false;
        vistied[r][c]=true;
        for (int i = 0; i < direct.length; i++) {
            int x=r+direct[i][0];
            if(x<0||x>=rLen)
                continue;
            int y=c+direct[i][1];
            if(y<0||y>=cLen)
                continue;
            if(vistied[x][y])
                continue;
            if(helper(board,vistied,word,wIndex+1,x,y))
                return true;
        }
        vistied[r][c]=false;
        return false;
    }

    public boolean exist2(char[][] board, String word) {
        int m = board.length;
        if (m == 0) {
            return false;
        }
        int n = board[0].length;
        boolean[][] marked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board,marked,word,i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board,boolean[][] marked,String word,int i, int j, int start) {
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        int rLen=board.length;
        int cLen=board[0].length;
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direct[k][0];
                if(newX<0||newX>=rLen)
                    continue;
                int newY = j + direct[k][1];
                if(newY<0||newY>=cLen)
                    continue;
                if (!marked[newX][newY]) {
                    if (dfs(board,marked,word,newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }


    public static void main(String[] args){
        No79_exist test=new No79_exist();
//        char[][]  board =
//                {
//                        {'A','B','C','E'},
//                        {'S','F','C','S'},
//                        {'A','D','E','E'}
//                };
//        String word = "ABCCED";

        char[][] board={{'a'}};
        String word="a";

//        char[][] board={
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        };
//        String word="ABCD";
        boolean exist = test.exist(board, word);
        System.out.println(exist);

        boolean b = test.exist2(board, word);
        System.out.println(b);
    }
}
