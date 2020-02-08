package com.luo.leetcode.trace;


import com.luo.util.CommonUtil;

/*
    37. 解数独
    编写一个程序，通过已填充的空格来解决数独问题。
        一个数独的解法需遵循如下规则：
        数字 1-9 在每一行只能出现一次。
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
        空白格用 '.' 表示。
        Note:
        给定的数独序列只包含数字 1-9 和字符 '.' 。
        你可以假设给定的数独只有唯一解。
        给定数独永远是 9x9 形式的。
*/
public class No37_SolveSudoku2 {
    private int n=3;
    private int N=3*n;
    private int[][] rows=new int[N][N+1];
    private int[][] columns=new int[N][N+1];
    private int[][] boxes=new int[N][N+1];
    private char[][] mboard=new char[N][N];
    private boolean isSolved=false;

    public void solveSudoku(char[][] board) {
        this.mboard=board;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j]!='.'){
                    int number=Character.getNumericValue(board[i][j]);
                    pinpointNumber(number,i,j);
                }
            }
        }
        trace(0,0);
    }

    private void trace(int row,int column){
        if(mboard[row][column]=='.'){
            for(int i=1,len=10;i<len;i++){
                if(canPlace(i,row,column)){
                    pinpointNumber(i,row,column);
                    traceNext(row,column);
                    if(!isSolved)
                        removeNumber(i,row,column);
                }
            }
        }else
            traceNext(row,column);
    }

    private boolean canPlace(int number,int row,int column){
        int boxesIndex=(row/n)*n+column/n;
        return rows[row][number]+columns[column][number]+boxes[boxesIndex][number]==0;
    }

    private void traceNext(int row,int column){
        if(row==N-1&&column==N-1){
            isSolved=true;
            return;
        }
        if(column==N-1)
//            这里是 trace(row+1,0) 这是因为当来到 (row,8)的位置时,已经不能再向右计算了,需要计算下一行,下一行的第一个元素
            trace(row+1,0);
        else
//            这里不满足
            trace(row,column+1);
    }

    private void pinpointNumber(int number,int row,int column){
        rows[row][number]++;
        columns[column][number]++;
        int boxesIndex=(row/n)*n+column/n;
        boxes[boxesIndex][number]++;
        mboard[row][column]=(char)(number+'0');
    }

    private void removeNumber(int number,int row,int column){
        rows[row][number]--;
        columns[column][number]--;
        int boxesIndex=(row/n)*n+column/n;
        boxes[boxesIndex][number]--;
        mboard[row][column]='.';
    }

    public static void main(String[] args){
        char[][] board={
                {'5','3','.'    ,'.','7','.'    ,'.','.','.'},
                {'6','.','.'    ,'1','9','5'    ,'.','.','.'},
                {'.','9','8'    ,'.','.','.'    ,'.','6','.'},

                {'8','.','.'    ,'.','6','.'    ,'.','.','3'},
                {'4','.','.'    ,'8','.','3'    ,'.','.','1'},
                {'7','.','.'    ,'.','2','.'    ,'.','.','6'},

                {'.','6','.'    ,'.','.','.'    ,'2','8','.'},
                {'.','.','.'    ,'4','1','9'    ,'.','.','5'},
                {'.','.','.'    ,'.','8','.'    ,'.','7','9'},
        };
        No37_SolveSudoku2 test=new No37_SolveSudoku2();
        CommonUtil.display(board);
        test.solveSudoku(board);
        CommonUtil.display(board);


    }

}
