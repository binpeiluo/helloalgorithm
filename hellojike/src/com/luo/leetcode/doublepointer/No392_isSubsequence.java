package com.luo.leetcode.doublepointer;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），
 * 而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）
 * 字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 *
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
 * 在这种情况下，你会怎样改变代码？
 *
 */
public class No392_isSubsequence {

    /**
     * 双指针
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int lens=s.length(),lent=t.length();
        if(lens==0){
            return true;
        }
        int j=0;
        for (int i = 0; i < lent; i++) {
            if(s.charAt(j)==t.charAt(i)){
                j++;
                if(j==lens){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 想要完成挑战难度,要使用动态规划
     * 大概意思是使用解法一的双指针方法,耗时在字符串t中找出下一个和字符串s中字符匹配的字符.此时有可能做多次循环
     * 这里使用动态规划 dp记录从i位置开始,字符j的第一次出现的位置.如此只需要访问dp数组就可能定位字符串t的指针
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] dp = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    dp[i][j] = i;
                else
                    dp[i][j] = dp[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (dp[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = dp[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    public static void main(String[] args){
        No392_isSubsequence test=new No392_isSubsequence();
//        String s = "axc", t = "ahbgdc";
        String s = "abc", t = "ahbgdc";

        boolean subsequence = test.isSubsequence(s, t);
        System.out.println(subsequence);
    }
}
