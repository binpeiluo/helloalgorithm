package com.luo.leetcode.bfs;

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 529. 扫雷游戏
 *
 * 让我们一起来玩扫雷游戏！
 * 给定一个代表游戏板的二维字符矩阵。 
 * 'M' 代表一个未挖出的地雷，
 * 'E' 代表一个未挖出的空方块，
 * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，
 * 'X' 则表示一个已挖出的地雷。
 *
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），
 * 根据以下规则，返回相应位置被点击后对应的面板：
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），
 *      并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），
 *      表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *  
 *
 * 示例 1：
 *
 * 输入:
 *
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 *
 * Click : [3,0]
 *
 * 输出:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 *
 * 示例 2：
 * 输入:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Click : [1,2]
 * 输出:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 */
@SuppressWarnings("Duplicates")
public class No529_updateBoard {


    public char[][] updateBoard(char[][] board, int[] click) {

        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }

        int m=board.length;
        int n=board[0].length;
//        辅助数组,记录相邻地雷数
        int[][] niber=new int[m][n];
        int[][] direct={
                {-1,-1},{-1,0},{-1,1},
                {0,-1},{0,1},
                {1,-1}, {1,0}, {1,1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]!='M'){
                    continue;
                }
                for (int k = 0; k < direct.length; k++) {
                    int x=i+direct[k][0];
                    if(x<0||x>=m){
                        continue;
                    }
                    int y=j+direct[k][1];
                    if(y<0||y>=n){
                        continue;
                    }
                    niber[x][y]++;
                }
            }
        }
//        从点击的位置开始dfs,只要相邻的位置没有附近地雷数量就可以入队
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(click);
//        board[click[0]][click[1]]='B';
//        并不能直接设置为 B
//        有可能是附近有地雷的
        if(niber[click[0]][click[1]]!=0){
            board[click[0]][click[1]]=(char)('0'+niber[click[0]][click[1]]);
        }else{
            board[click[0]][click[1]]='B';
        }

//        需要更新相邻地雷数量
        List<int[]> updateCnt=new LinkedList<>();
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            for (int i = 0; i < direct.length; i++) {
                int x=poll[0]+direct[i][0];
                if(x<0||x>=m){
                    continue;
                }
                int y=poll[1]+direct[i][1];
                if(y<0||y>=n){
                    continue;
                }

//                附近是地雷
                if(board[x][y]=='M'){
                    continue;
                }
//                只有原来的位置没有地雷,且附近的位置提示相邻地雷才更新
                if(niber[poll[0]][poll[1]]==0&&niber[x][y]!=0){
                    updateCnt.add(new int[]{x,y});
                    continue;
                }
                if(board[x][y]=='E'&&niber[poll[0]][poll[1]]==0){
                    board[x][y]='B';
                    queue.offer(new int[]{x,y});
                }
            }
        }
        for (int[] u:updateCnt){
            board[u[0]][u[1]]=(char)('0'+niber[u[0]][u[1]]);
        }
        return board;
    }

    public static void main(String[] args){
        No529_updateBoard test=new No529_updateBoard();
//        char[][] board= {
//                {'E', 'E', 'E', 'E', 'E'},
//                {'E', 'E', 'M', 'E', 'E'},
//                {'E', 'E', 'E', 'E', 'E'},
//                {'E', 'E', 'E', 'E', 'E'}
//        };
//        int[] click={3,0};

//        char[][] board= {
//                {'B', '1', 'E', '1', 'B'},
//                {'B', '1', 'M', '1', 'B'},
//                {'B', '1', '1', '1', 'B'},
//                {'B', 'B', 'B', 'B', 'B'}
//        };
//        int[] click={1,2};

//        char[][] board= {
//                {'B','B','B','B','B','B','1','E'},
//                {'B','1','1','1','B','B','1','M'},
//                {'1','2','M','1','B','B','1','1'},
//                {'M','2','1','1','B','B','B','B'},
//                {'1','1','B','B','B','B','B','B'},
//                {'B','B','B','B','B','B','B','B'},
//                {'B','1','2','2','1','B','B','B'},
//                {'B','1','M','M','1','B','B','B'}
//        };
//        int[] click={0,7};
//        输出
//        B	B	B	B	B	B	1	B
//        B	1	1	1	B	B	1	M
//        1	2	M	1	B	B	1	1
//        M	2	1	1	B	B	B	B
//        1	1	B	B	B	B	B	B
//        B	B	B	B	B	B	B	B
//        B	1	2	2	1	B	B	B
//        B	1	M	M	1	B	B	B

//        期望
//        B	B	B	B	B	B	1	1
//        B	1	1	1	B	B	1	M
//        1	2	M	1	B	B	1	1
//        M	2	1	1	B	B	B	B
//        1	1	B	B	B	B	B	B
//        B	B	B	B	B	B	B	B
//        B	1	2	2	1	B	B	B
//        B	1	M	M	1	B	B	B

        char[][] board= {
                {'E','M','M','2','B','B','B','B'},
                {'E','E','M','2','B','B','B','B'},
                {'E','E','2','1','B','B','B','B'},
                {'E','M','1','B','B','B','B','B'},
                {'1','2','2','1','B','B','B','B'},
                {'B','1','M','1','B','B','B','B'},
                {'B','1','1','1','B','B','B','B'},
                {'B','B','B','B','B','B','B','B'}
        };
        int[] click={0,0};
//        输出
//        1	2	M	2	B	B	B	B
//        1	3	M	2	B	B	B	B
//        E	E	2	1	B	B	B	B
//        E	M	1	B	B	B	B	B
//        1	2	2	1	B	B	B	B
//        B	1	M	1	B	B	B	B
//        B	1	1	1	B	B	B	B
//        B	B	B	B	B	B	B	B

//        期望
//        1	M	M	2	B	B	B	B
//        E	E	M	2	B	B	B	B
//        E	E	2	1	B	B	B	B
//        E	M	1	B	B	B	B	B
//        1	2	2	1	B	B	B	B
//        B	1	M	1	B	B	B	B
//        B	1	1	1	B	B	B	B
//        B	B	B	B	B	B	B	B



        test.updateBoard(board,click);

        CommonUtil.display(board);

    }
}
