package com.luo.leetcode.dp;

/**
 * LCP 19. 秋叶收藏集
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
 * 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
 * 每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
 * 请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 *
 * 示例 1：
 * 输入：leaves = "rrryyyrryyyrr"
 * 输出：2
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 *
 * 示例 2：
 * 输入：leaves = "ryr"
 * 输出：0
 * 解释：已符合要求，不需要额外操作
 *
 * 提示：
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 *
 */
public class No_LCP19_minimumOperations {

    public int minimumOperations(String leaves) {
//        动态规划
//        1 确定状态,当前位置和当前颜色状态 使用0表示最前边的红色,使用1表示中间的红色,使用2表示右边的红色
//          dp[i][j] 表示 leaves[0]到leaves[i] 进行的操作次数,并且leaves[i]==j状态
//        2 明确dp函数
//        3 状态转移方程
//          当j=0时,dp[i][0]=dp[i-1][0]+isYellow(leaves[i]) 状态0只能用状态0转移过来,不能由状态1或者2转移而来
//          当j=1时,dp[i][1]=min(dp[i-1][1],dp[i-1][0])+isRed(leaves[i]) 状态1可以从状态1或者状态0转移而来
//          当j=2时,dp[i][2]=min(dp[i-1][1],dp[i-1][2])+isYellow(leaves[i]) 状态2可以从状态2或者状态1转移而来
//        4 base case
//          dp[i][j] j=3,那么只有在i>=3-1时才有意义,并且必须满足 i>=j
//          状态转移方程中有min函数,那么一开始设置一个MAX的默认值即可
//          状态转移方程中,只有当i-1>=0才有意义
//
        int len=leaves.length();
        int[][] dp=new int[len][3];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        dp[0][0]=leaves.charAt(0)=='y'?1:0;
        for (int i = 1; i < len; i++) {
            dp[i][0]=dp[i-1][0]+ (leaves.charAt(i)=='y'?1:0);
        }
//        注意dp[i][j]只有在i>=j才有意义
        for (int i = 1; i < len; i++) {
            dp[i][1]=Math.min(i-2>=0?dp[i-1][1]:Integer.MAX_VALUE,dp[i-1][0])+ (leaves.charAt(i)=='r'?1:0);
        }
        for (int i = 2; i < len; i++) {
            dp[i][2]=Math.min(i-3>=0?dp[i-1][2]:Integer.MAX_VALUE,dp[i-1][1])+ (leaves.charAt(i)=='y'?1:0);
        }
        return dp[len-1][2];
    }


    /**
     * 优化版本 不需要多次遍历
     * @param leaves
     * @return
     */
    public int minimumOperations2(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < len; ++i) {
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        return dp[len - 1][2];
    }

    public static void main(String[] args) {
        No_LCP19_minimumOperations test=new No_LCP19_minimumOperations();

        String leaves = "rrryyyrryyyrr";
//        String leaves = "ryr";

        int i = test.minimumOperations(leaves);
        System.out.println(i);
    }


}
