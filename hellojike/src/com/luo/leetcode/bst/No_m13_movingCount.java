package com.luo.leetcode.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 面试题13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class No_m13_movingCount {

    /**
     * 想到的方法是bfs,也可以使用递推,对于每个位置,判断上边和左边是否能到达,能的话判断自己这个位置是否符合k的要求
     *
     * 时间复杂度:   O(m*n)  queue的元素个数
     * 空间复杂度:   O(m*n)  visited辅助数组
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] vistied=new boolean[m][n];
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{0,0});
        vistied[0][0]=true;
        int res=1;
        int[][] direct={{0,1},{1,0}};
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            for (int i = 0; i < direct.length; i++) {
                int x=poll[0]+direct[i][0];
                if(x<0||x>=m)
                    continue;
                int y=poll[1]+direct[i][1];
                if(y<0||y>=n)
                    continue;
                if(!canMove(x,y,k)||vistied[x][y])
                    continue;
                queue.offer(new int[]{x,y});
                vistied[x][y]=true;
                res++;
            }
        }
        return res;
    }

    private boolean canMove(int i,int j,int k){
        char[] chars = (i + "" + j).toCharArray();
        int sum=0;
        for (int l = 0; l < chars.length; l++) {
            sum+=chars[l]-'0';
        }
        return sum<=k;
    }

    /**
     * 使用递推
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount2(int m,int n,int k){
        boolean[][] canVisited=new boolean[m][n];
        canVisited[0][0]=true;
        int res=1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                判断左边和上边两个元素是否可达
                boolean left=j>0?canVisited[i][j-1]:false;
                boolean up=i>0?canVisited[i-1][j]:false;
                if((left||up)&& helper2(i,j,k)){
                    res++;
                    canVisited[i][j]=true;
                }
            }
        }
        return res;
    }

    private boolean helper2(int m,int n,int k){
        int sum=0;
        for (;m>0;m/=10)
            sum+=m%10;
        for (;n>0;n/=10)
            sum+=n%10;
        return sum<=k;
    }

    public static void main(String[] args){
        No_m13_movingCount test=new No_m13_movingCount();
        int m = 2, n = 3, k = 1;
//        int m = 3, n = 1, k = 0;
        int i = test.movingCount(m, n, k);
        System.out.println(i);

        int i1 = test.movingCount2(m, n, k);
        System.out.println(i1);
    }
}
