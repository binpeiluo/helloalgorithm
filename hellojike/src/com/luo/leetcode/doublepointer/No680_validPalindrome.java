package com.luo.leetcode.doublepointer;

/*

680. 验证回文字符串 Ⅱ
给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

示例 1:

输入: "aba"
输出: True
示例 2:

输入: "abca"
输出: True
解释: 你可以删除c字符。
注意:

字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
*/

public class No680_validPalindrome {

    /**
     * 回文问题一看到就应该想到双指针.难点在于如何排除一个字符
     * !!!看到别人的实现方式,使用两个指针在两端,然后当这两个指针 i,j 指向字符不一致时
     * 判断 (i,j-1)或者(i+1,j)是否是回文
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int len=s.length();
        if(len<3)
            return true;
        int left=0,right=len-1;
        while(left<right&&s.charAt(left)==s.charAt(right)){
            left++;
            right--;
        }
        if(left>=right)
            return true;
        else
            return isPalindrome(s,left,right-1)||isPalindrome(s,left+1,right);
    }

    private boolean isPalindrome(String s,int i,int j){
        while(i<j&&s.charAt(i)==s.charAt(j)){
            i++;
            j--;
        }
        if(i>=j)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        No680_validPalindrome test=new No680_validPalindrome();
        String str="abc";
        boolean b = test.validPalindrome(str);
        System.out.println(b);
    }
}
