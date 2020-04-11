package com.luo.leetcode.dp;

import java.util.Arrays;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * 示例 1：
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 *
 */
public class No887_superEggDrop {

    /**
     * 没解出来
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
//        状态,楼层高度,鸡蛋数量
//        明确dp函数含义,dp[i][j]表示楼层为i,鸡蛋数为j时,至少需要扔dp[i][j]次才能确定f
//        状态转移 dp[i][j]=min(dp[n-i][j]+1,dp[i-1][j-1]+1)
//          dp[n-i][j]+1 代表没碎,在剩下的n-i层楼,j个鸡蛋继续推算
//          dp[i-1][j-1]+1 代表碎了,在剩下的i-1层楼,j-1个鸡蛋继续推算
//        base case dp[i][1]=i

        int[][] dp=new int[N+1][K+1];
        for (int i = 0; i < N; i++) {
            dp[i][0]=Integer.MAX_VALUE;
            dp[i][1]=i;
        }
        for (int i = 1; i <=N; i++) {
            for (int j = 1; j <=K; j++) {
//                这里其实不能这么处理,根据递推公司 dp[i][j]取决于dp[i-1][j-1]和dp[n-i][j] 故这么推是不正确的
                dp[i][j]=Math.min(dp[i-1][j-1],dp[N-i][j])+1;
            }
        }
        return dp[N][K];
    }

    /**
     * 自己的想法修改后
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop2(int K, int N) {
        int[][] dp=new int[K+1][N+1];
        for (int i = 0; i < K + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        return helper2(dp,K,N);
    }

    private int helper2(int[][] dp,int k,int n){
        if(k==1)
            return n;
        if(n==0)
            return 0;
        if(dp[k][n]!=Integer.MAX_VALUE)
            return dp[k][n];
        int res=Integer.MAX_VALUE;
        for (int i = 1; i <=n; i++) {
            res=Math.min(res,//在第i层扔鸡蛋
                    Math.max(//这里为什么是max,因为答案是求最坏情况下的次数
                            helper2(dp,k-1,i-1),//碎了,在剩下的i-i层继续扔鸡蛋
                            helper2(dp,k,n-i) //没碎,在剩下的 n-i层继续扔鸡蛋
                    )+1
            );
        }
        dp[k][n]=res;
        return res;
    }

    public static void main(String[] args){
        No887_superEggDrop test=new No887_superEggDrop();
//        int K = 1, N = 2;
//        int K = 2, N = 6;
//        int K = 3, N = 14;
        int K = 5, N = 10000;
        int i = test.superEggDrop(K, N);
        System.out.println(i);

        int i1 = test.superEggDrop2(K, N);
        System.out.println(i1);
    }
}
