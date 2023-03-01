package com.luo.algorithm.dp;

/**
 * 最长回文子序列长度
 */
public class TestHuiwen {

    /**
     * 启发很大
     *  四步走.
     *      1 确定状态
     *      2 明确dp函数定义
     *      3 状态如何转移
     *      明确base case
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
//        1 确定状态
//            子序列起始和末尾位置,需要两个状态.需要二维数组
//        2 明确dp函数定义.dp[i][j]是子序列s[i][j]的最长回文子序列长度
//        3 状态如何转移.如何通过已经的答案推算出未知的问题
//            比如求dp[i][j],已知了dp[i+1][j-1]=3 如何计算dp[i][j]
//                当s[i]==s[j] 时,这两个字符可以为回文的一部分,所以dp[i][j]=d[i+1][j-1]+2
//                当s[i]!=s[j] 时,这两个字符不可能给同时为回文的一部分,分别让其加入到dp[i+1][j-1]中
//        4 base case
//            当i>j时,没有如何意义.
//            当i==j时,dp[i][j]=1
//            dp[i][j]的推导需要 dp[i+1][j-1],dp[i+1][j],dp[i][j-1] .考虑如何遍历
        int len =s.length();
        if(len==1)
            return 1;
        int[][] dp=new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i]=1;
        }
        for (int i = 0; i < len - 1; i++) {
            int m=len-1-1-i;
            for (int j = m+1; j < len; j++) {
                if(s.charAt(m)==s.charAt(j)){
                    dp[m][j]=dp[m+1][j-1]+2;
                }else{
                    dp[m][j]=Math.max(dp[m+1][j-1],dp[m][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }


    public static void main(String[] args){
        TestHuiwen test=new TestHuiwen();
        String target="cbbd";
        int i = test.longestPalindromeSubseq(target);
        System.out.println(i);
    }
}
