package com.luo.leetcode.dp;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class No516_longestPalindromeSubseq {

    /**
     * 典型动态规划
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
//        定义       dp[i][j] 表示字符串 s[i:j] 之间能组成的最大回文串长度
//        状态转移    dp[i][j] = dp[i+1][j-1] if s[i]==s[j]
//                           = max(dp[i][j-1],dp[i+1][j])  此时s[i],s[j]不能同时作为回文字符串的首尾位置
//        base case dp[i][i] = 1
//        初始条件
//        注意这里的依赖关系 [i][j-1],[i+1][j-1],[i+1][j] -> [i][j]
//                      所以 i 需要从 n -> 0
//                          j 需要从 0 -> n
        int len=s.length();
        int[][] dp=new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i]=1;
        }
        for (int i = len-1; i >=0 ; i--) {
            for (int j = i+1; j < len; j++) {
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
//                    这里需要判断越界吗 不需要 因为 i=len-1时, 不会走到这个逻辑里边
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

}
