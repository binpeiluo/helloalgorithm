package com.luo.leetcode.dp;

import com.luo.util.CommonUtil;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 */
public class No72_minDistance {
    /**
     * 典型动态规划问题
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
//        确定状态,两个单词的角标
//        明确dp数组的含义,dp[i][j]表示w1前i个字符和w2前j个字符的编辑距离
//        状态转移
//        bad case 当i==0则dp[i][j]=j
        if(word1.length()==0)
            return word2.length();
        if(word2.length()==0)
            return word1.length();
        int len1=word1.length();
        int len2=word2.length();
        int[][] dp=new int[len1+1][len2+1];
//        处理bad case
        for (int i = 0; i <=len1; i++) {
            dp[i][0]=i;
        }
        for (int i = 0; i <=len2; i++) {
            dp[0][i]=i;
        }
        for (int i = 0; i <len1; i++) {
            for (int j = 0; j <len2; j++) {
                if(word1.charAt(i)==word2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j];
                }else{
                    int r=0;
                    r=Math.min(dp[i][j],dp[i][j+1]);
                    r=Math.min(r,dp[i+1][j]);
                    dp[i+1][j+1]=r+1;
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args){
        No72_minDistance test=new No72_minDistance();
        String w1="horse";
        String w2="ros";
        int i = test.minDistance(w1, w2);
        System.out.println(i);
    }
}
