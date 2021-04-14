package com.luo.leetcode.dp;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：
 *  它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 */
public class No1143_longestCommonSubsequence {

    /**
     * 思路 最长、最大等可以考虑动态规划
     * 明确变量：遍历text1和text2的每一个字母
     * 明确dp函数定义   dp[i][j] 表示 text1长度为i和text2长度为j的最长公共子序列长度
     * 明确选择  当text1[i]==text2[j] 时 dp[i][j]=dp[i-1][j-1]+1
     *          当text1[i]!=text2[j] 时 dp[i][j]=max(dp[i][j-1],dp[i-1][j])
     *          选择推导方向 从左到右 从上到下
     * base case
     *          dp[i][0]=0 dp[0][j]=0
     *          所求的答案即 dp[len(text1)][len(text2)]
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1=text1.length(),len2=text2.length();
        int[][] dp=new int[len1+1][len2+1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }else{
                    dp[i+1][j+1]=Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        No1143_longestCommonSubsequence test=new No1143_longestCommonSubsequence();
//        String text1 = "abcde", text2 = "ace";
//        String text1 = "abc", text2 = "abc";
        String text1 = "abc", text2 = "def";

        int i = test.longestCommonSubsequence(text1, text2);
        System.out.println(i);


    }
}
