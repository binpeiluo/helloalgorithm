package com.luo.leetcode.doublepointer;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 */
public class No15_addStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int num1Index=num1.length()-1;
        int num2Index=num2.length()-1;
        int carry=0;
        while(true){
            if(num1Index<0&&num2Index<0){
                if(carry!=0){
                    sb.insert(0,carry);
                }
                break;
            }
            int t=0;
            int num1N=num1Index<0?0:(num1.charAt(num1Index--)-'0');
            int num2N=num2Index<0?0:(num2.charAt(num2Index--)-'0');
            t+=num1N;
            t+=num2N;
            t+=carry;
//            每次计算进位
            carry=t/10;
//            每次写入一位
            sb.insert(0,t%10);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        No15_addStrings test=new No15_addStrings();
        String str1="99";
        String str2="9";
        String s = test.addStrings(str1, str2);
        System.out.println(s);
    }
}
