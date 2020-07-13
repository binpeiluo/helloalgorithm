package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

import java.util.Arrays;

/**
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *  
 *
 * 说明:
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 */
public class No174_calculateMinimumHP {


    /**
     * 动态规划问题.重叠子问题,最优子结构
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length;
        int n=dungeon[0].length;
//        状态,骑士的位置.
//        定义dp函数,dp[i][j] 表示骑士来到dungeon[i][j]最少需要的生命值
//        选择,骑士可以向下走一步或者向右走一步
//        状态转移, dp[i][j]=Math.max(Math.min(dp[i-1][j],dp[i][j-1])-dungeon[i][j],1)
//        base case
        int[][] dp=new int[m][n];
        dp[0][0]=Math.max(1-dungeon[0][0],1);
        for (int i = 1; i < m; i++) {
            dp[i][0]=Math.max(dp[i-1][0]-dungeon[i][0],dp[i-1][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j]=Math.max(dp[0][j-1]-dungeon[0][j],dp[0][j-1]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=Math.max(Math.min(dp[i-1][j],dp[i][j-1])-dungeon[i][j],Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }

        CommonUtil.display(dp);
        return dp[m-1][n-1];
    }

    public int calculateMinimumHP2(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        CommonUtil.display(dp);
        return dp[0][0];
    }



    public static void main(String[] args){
        No174_calculateMinimumHP test=new No174_calculateMinimumHP();
        int[][] dungeon={
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };

        int i = test.calculateMinimumHP(dungeon);
        System.out.println(i);

        int i1 = test.calculateMinimumHP2(dungeon);
        System.out.println(i1);
    }
}
