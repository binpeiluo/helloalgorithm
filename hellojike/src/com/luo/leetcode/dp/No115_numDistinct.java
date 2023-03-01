package com.luo.leetcode.dp;

/**
 * 115. 不同的子序列
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 */
public class No115_numDistinct {

    /**
     * 动态规划
     * 动态规划的核心在于穷举,然而动态规则具有重叠子问题(会倒置重复计算,可以使用备忘录储存).
     *  另外具有最右子结构,分解的子问题的最优化能推出当前问题的最优解.
     *  另外需要有状态转移方程
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
//        明确变量 dp[i][j]表示s[i:]的子序列中出现t[j:]的次数
        int m=s.length(),n=t.length();

        if(m<n){
            return 0;
        }

        int[][] dp=new int[m+1][n+1];
//        那么问题就是求解dp[0][0]
//        考虑s[i]=t[j]时,有两种选择(要么认为该字符匹配,进行下一个字符匹配.要么当做不匹配). dp[i][j]=dp[i+1][j+1]+dp[i+1][j]
//        考虑s[i]!=t[j]时,没得选,dp[i][j]=dp[i+1][j]

//        考虑base case, 当dp[i][n]=1, dp[m][j]=0

        for (int i = 0; i <=m; i++) {
            dp[i][n]=1;
        }

//        考虑递归方向为从右到左从下到上

//        不是 dp[i][j]==0 当 i<j
        for (int i = m-1; i >=0 ; i--) {
            for (int j = n-1; j >=0 ; j--) {
                if(s.charAt(i)==t.charAt(j)){
                    dp[i][j]=dp[i+1][j+1]+dp[i+1][j];
                }else{
                    dp[i][j]=dp[i+1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        No115_numDistinct test=new No115_numDistinct();
//        String s = "rabbbit", t = "rabbit";

        String s = "babgbag", t = "bag";

        int i = test.numDistinct(s, t);
        System.out.println(i);
    }
}
