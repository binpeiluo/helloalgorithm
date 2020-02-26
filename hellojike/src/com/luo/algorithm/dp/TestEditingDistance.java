package com.luo.algorithm.dp;

import java.util.Arrays;

/**
 * 编辑距离
 */
public class TestEditingDistance {

    /**
     * 思路:
     *      自己尝试的解决方案.
     *      单纯动态规划,无备忘录.
     *      根据四步走方案.核心是明确dp函数定义以及状态转移
     * @param str1
     * @param str2
     * @return
     */
    public int editingDistance(String str1,String str2){
//        明确状态 假设状态为str1角标的位置和str2角标的位置
//        明确dp函数定义 ,str1的以第i个字符结尾和str2的以第j个字符结尾,这两个字符串至少需要的编辑距离为dp[i][j]
//        明确选择,并且择优.如何转移
//        明确 base case
        return dp(str1,str1.length()-1,str2,str2.length()-1);

    }

    private int dp(String str1,int i,String str2,int j){
        if(str1.charAt(i)==str2.charAt(j)){
            if(i==0)
                return j;
            else if(j==0)
                return i;
            else
                return dp(str1,i-1,str2,j-1);
        }else{
            if(j==0&&i==0)
                return 1;
            int res=Integer.MAX_VALUE;
            if(j-1>=0)
                res=Math.min(res,dp(str1,i,str2,j-1));
            if(i-1>=0)
                res=Math.min(res,dp(str1,i-1,str2,j));
            if(i-1>=0&&j-1>=0)
                res=Math.min(res,dp(str1,i-1,str2,j-1));
            return res+1;
        }
    }

    public int editingDistance2(String str1,String str2){
//        明确dp函数定义,str1的前i个字符和str2的前j个字符的编辑距离为dp[i][j]
        int m=str1.length(),n=str2.length();
        int[][] dp=new int[m+1][n+1];
//        base case
        for(int i=0;i<m;i++)
            dp[i][0]=i;
        for(int i=0;i<n;i++)
            dp[0][i]=i;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    int min=Math.min(dp[i][j-1],dp[i-1][j]);
                    min=Math.min(min,dp[i-1][j-1]);
                    dp[i][j]=min+1;
                }
            }
        }
        return dp[m][n];

    }

    public static void main(String[] args){
        TestEditingDistance test=new TestEditingDistance();
        String str1="intention";
        String str2="execution";
        int i = test.editingDistance(str1, str2);
        System.out.println(i);

        int i1 = test.editingDistance2(str1, str2);
        System.out.println(i1);
    }
}
