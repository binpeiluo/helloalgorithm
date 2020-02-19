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
            int res=Math.max(i,j);
            if(j-1>=0)
                res=Math.min(res,dp(str1,i,str2,j-1));
            if(i-1>=0)
                res=Math.min(res,dp(str1,i-1,str2,j));
            return res+1;
        }
    }

    public static void main(String[] args){
        TestEditingDistance test=new TestEditingDistance();
        String str1="intention";
        String str2="execution";
        int i = test.editingDistance(str1, str2);
        System.out.println(i);
    }
}
