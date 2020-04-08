package com.luo.leetcode.dp;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
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
 */
public class No44_isMatch {

    /**
     * 自己的思路
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(s.equals(p))
            return true;
        if("*".equals(p))
            return true;
        if("".equals(s)||"".equals(p))
            return false;
        if(p.charAt(0)=='?'||p.charAt(0)==s.charAt(0))
            return isMatch(s.substring(1),p.substring(1));
        if(p.charAt(0)=='*')
            return isMatch(s,p.substring(1))||isMatch(s.substring(1),p);
        else
            return p.charAt(0)==s.charAt(0)&&isMatch(s.substring(1),p.substring(1));
    }

    public static void main(String[] args){
        No44_isMatch test=new No44_isMatch();
        String s=null;
        String p=null;
//        s = "acdcb";
//        p = "a*c?b";
        s="ho";
        p="ho**";
        boolean match = test.isMatch(s, p);
        System.out.println(match);
    }
}
