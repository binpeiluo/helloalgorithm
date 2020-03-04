package com.luo.labuladong.highfrequence;

/**
 * 高频题目
 *      寻找最长回文子串,注意是子串并非子序列
 */
public class TestLongestHuiwen {

    /**
     * 思路
     *      可以使用动态规划,两个状态,起始和末尾字符
     *
     *      或者通过从每个字符为中心,寻找回文串
     *      但是需要注意回文串有可能长度是偶数,
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res="";
        for (int i = 0; i < s.length(); i++) {
            String t1 = helper(s, i, i);
            res=t1.length()>res.length()?t1:res;
            String t2 = helper(s, i, i + 1);
            res=t2.length()>res.length()?t2:res;
        }
        return res;
    }

    private String helper(String s,int i,int j){
        while(i>=0&&j<=s.length()-1&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
        return s.substring(i+1,j);
    }

    public static void main(String[] args){
        TestLongestHuiwen test=new TestLongestHuiwen();
        String str="ccbd";
        String s = test.longestPalindrome(str);
        System.out.println(s);
    }
}
