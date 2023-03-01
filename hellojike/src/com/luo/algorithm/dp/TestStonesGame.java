package com.luo.algorithm.dp;

/**
 * 石头游戏
 * 你和你的朋友⾯前有⼀排⽯头堆，⽤⼀个数组 piles 表
 * ⽰，piles[i] 表⽰第 i 堆⽯⼦有多少个。你们轮流拿⽯头，⼀次拿⼀堆，但是
 * 只能拿⾛最左边或者最右边的⽯头堆。所有⽯头被拿完后，谁拥有的⽯头
 * 多，谁获胜。
 */
public class TestStonesGame {
    /**
     *
     * @param piles
     * @return
     */
    public int stonesGame(int[] piles){
//        1 明确状态.本题的状态有三个,分别是 石头堆起始位置,石头堆结束位置,谁先取
//        2 明确dp函数定义,dp[i][j][2] 表示取第i堆到第j堆石头,
//          从先后两人的成绩分别在dp[i][j][0]和dp[i][j][1]
//        3 状态转移,重点在于如何模仿两人分别取石头,我们前边定义时使用长度为2的数组,[0]代表先手,[1]代表后手
//              dp[i][j][0]=max(piles[i]+dp[i+1][j][1],piles[j]+dp[i][j-1][1])
//              dp[i][j][1]=max(piles[i]+dp[i+1][j][0],piles[j]+dp[i][j-1][0])
//        4 base case
//            0<i<=j=len
//            当i=j,dp[i][j][0]=piles[i],dp[i][j][1]=0
//            另外由于依赖关系,需要注意遍历方向
        int len=piles.length;
        int[][][] dp=new int[len][len][2];
        for (int i = 0; i < len; i++) {
            dp[i][i][0]=piles[i];
            dp[i][i][1]=0;
        }
//        斜着遍历,果然不是很easy
        for (int c = 0; c < len - 1; c++) {
//            c 代表外层循环的次数
            for (int i = 0; i < len-1 - c; i++) {
//                i 代表内层循环的竖轴,
                int j=i+c+1;

                int left=piles[i]+dp[i+1][j][1];
                int right=piles[j]+dp[i][j-1][1];
                if(left>right){
                    dp[i][j][0]=left;
                    dp[i][j][1]=dp[i+1][j][0];
                }else{
                    dp[i][j][0]=right;
                    dp[i][j][1]=dp[i][j-1][0];
                }
            }
        }

        return dp[0][len-1][0]-dp[0][len-1][1];
    }

    public int stonesGame2(int[] piles){

        int len=piles.length;
        int[][][] dp=new int[len][len][2];
        for (int i = 0; i < len; i++) {
            dp[i][i][0]=piles[i];
            dp[i][i][1]=0;
        }
//        试着横着遍历
        for (int i = len-2; i >=0; i--) {
            for (int j = i+1; j < len; j++) {
                int left = piles[i] + dp[i+1][j][1];
                int right = piles[j] + dp[i][j-1][1];
//              套⽤状态转移⽅程
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i+1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }

        return dp[0][len-1][0]-dp[0][len-1][1];
    }

    /* 返回游戏最后先⼿和后⼿的得分之差 */
    int stoneGameLa(int[] piles) {
        int n = piles.length;
//     初始化 dp 数组
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++){
                dp[i][j][0] = 0;
                dp[i][j][1] = 0;
            }
//      填⼊ base case
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }
//      斜着遍历数组
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = l + i - 1;
//              先⼿选择最左边或最右边的分数
                int left = piles[i] + dp[i+1][j][1];
                int right = piles[j] + dp[i][j-1][1];
//              套⽤状态转移⽅程
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i+1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }
        return dp[0][n-1][0]-dp[0][n-1][1];
    }

    public static void main(String[] args){
        TestStonesGame test=new TestStonesGame();
//        int[] piles={1,100,3};
        int[] piles={2,1,90,30};
        int i = test.stonesGame(piles);
        System.out.println(i);

        int i2 = test.stonesGame2(piles);
        System.out.println(i2);

        int i1 = test.stoneGameLa(piles);
        System.out.println(i1);
    }
}
