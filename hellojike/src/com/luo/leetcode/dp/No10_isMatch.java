package com.luo.leetcode.dp;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 */
public class No10_isMatch {

    /**
     * 正则表达式判断两个字符串匹配问题.
     * 可以很明显看出子问题,当第一个字符匹配时,只需再匹配下一个字符是否匹配即可.
     * 那书否有重复子问题的性质呢? 根据递推公式,dp(i,j)的解有多条路径.则可以说明有重复子问题
     *  dp(i,j):
     *      dp(i,j+2)
     *      dp(i+1,j)
     *      dp(i+1,j+1)
     * 即该题符合动态规划性质
     *
     * navie的方式在栈深度过深时可能造成栈溢出,可以使用memo记忆优化
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        boolean firstMatch=(!s.isEmpty()&&p.charAt(0)=='.')||s.charAt(0)==p.charAt(0);
        if(p.length()>=2&&p.charAt(1)=='*'){
//            当存在*通配符时,可以匹配也可以不匹配. *可以匹配0到多次
            boolean not=isMatch(s,p.substring(2));
//            不匹配
            boolean is=firstMatch&&isMatch(s.substring(1),p);
            return not||is;
        }else{
            return firstMatch&&isMatch(s.substring(1),p.substring(1));
        }
    }

    /**
     * 尝试使用dp数组优化
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
//        确定状态,状态为两个字符串角标
//        确定dp函数,dp(i,j)表示字符串s前i个字符和字符串p前j个字符是否匹配
//        状态转移,注意遍历计算方向
//            dp(i,j):
//                dp(i-1,j-1),
//                dp(i,j-2),
//                dp(i-1,j)
//        明确 bad case
        int sLen=s.length();
        int pLen=p.length();
        boolean[][] dp=new boolean[sLen+1][pLen+1];
        dp[0][0]=true;
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                boolean firstMatch=p.charAt(j)=='.'||s.charAt(i)==p.charAt(j);

            }
        }
        return true;

    }

    public static void main(String[] args){
        No10_isMatch test=new No10_isMatch();
        String s;
        String p;
        s = "mississippi";
        p = "mis*is*p*.";
        boolean match = test.isMatch(s, p);
        System.out.println(match);
    }
}
