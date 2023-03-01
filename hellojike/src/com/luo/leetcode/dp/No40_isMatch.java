package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 *
 */
public class No40_isMatch {
    /**
     * 思路:
     *      使用动态规划
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
//        确定状态 两个字符串角标
//        明确dp数组定义,dp[i][j]表示字符串s的前i个字符和字符串p的前j个字符是否匹配
//        状态转移  dp[i][j]=dp[i-1][j-1], if s[i-1]==p[i-1] or p[i-1]=='?'
//                    dp[i][j]=dp[i-1][j] or dp[i][j-1] if p[i-1]=='*'
//        base case dp[0][0]=true,dp[0][i]=? ,dp[i][0]=?
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp=new boolean[sLen+1][pLen+1];
        dp[0][0]=true;
//        base case ,dp[0][i]和dp[i][0]
        for (int i = 1; i <=pLen; i++) {
            dp[0][i]=dp[0][i-1]&&p.charAt(i-1)=='*';
        }
        for (int i = 1; i <=sLen; i++) {
            for (int j = 1; j <=pLen; j++) {
//                考虑 ?
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?')
                    dp[i][j]=dp[i-1][j-1];
//                考虑 * ,* 可以匹配也可以不匹配
                else if(p.charAt(j-1)=='*')
//                    假如p[j-1]=='*',当*不参与匹配时,dp[i][j]=dp[i-1][j]
//                    当*参与匹配时,dp[i][j]=dp[i][j-1]
                    dp[i][j]=dp[i][j-1] || dp[i-1][j];
//                其余情况都是false,不需要再赋值
            }
        }
        return dp[sLen][pLen];
    }

    public static void main(String[] args){
        No40_isMatch test=new No40_isMatch();
        String s;
        String p;
//        s = "adceb";
//        p = "*a*b";
        s = "acdcb";
        p = "a*c?b";
        boolean match = test.isMatch(s, p);
        System.out.println(match);
    }
}
